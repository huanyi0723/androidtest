# ImageView

# 属性
- android:adjustViewBounds //是否调整自己边界来保持图片长宽比
- android:cropToPadding //裁剪
- android:maxHeight //最大高度
- android:maxWidth //最大宽度
- android:scaleType //如何缩放 matrix fitXY fitStart fitCenter fitEnd center centerCrop centerInside
- android:src //显示图像


<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="fill_parent"
    android:layout_height="fill_parent"
    android:orientation="vertical" >

    <LinearLayout
        android:layout_width="fill_parent"
        android:layout_height="wrap_content"
        android:gravity="center"
        android:orientation="horizontal" >

        <Button
            android:id="@+id/plus"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="增大透明度" />

        <Button
            android:id="@+id/minus"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="降低透明度" />

        <Button
            android:id="@+id/next"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="下一张" />
    </LinearLayout>
    <!-- 定义显示图片整体的ImageView -->

    <ImageView
        android:id="@+id/image1"
        android:layout_width="fill_parent"
        android:layout_height="200dp"
        android:scaleType="fitCenter"
        android:src="@drawable/shuangta" />
    <!-- 定义显示图片局部细节的ImageView -->

    <ImageView
        android:id="@+id/image2"
        android:layout_width="120dp"
        android:layout_height="120dp"
        android:layout_marginTop="10dp"
        android:background="#00f" />

</LinearLayout>


public class ImageViewTest extends Activity {
	// 定义一个访问图片的数组
	int[] images = new int[] { R.drawable.lijiang, R.drawable.qiao, R.drawable.shuangta, R.drawable.shui,
			R.drawable.xiangbi, };
	// 定义默认显示的图片
	int currentImg = 2;
	// 定义图片的初始透明度
	private int alpha = 255;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		final Button plus = (Button) findViewById(R.id.plus);
		final Button minus = (Button) findViewById(R.id.minus);
		final ImageView image1 = (ImageView) findViewById(R.id.image1);
		final ImageView image2 = (ImageView) findViewById(R.id.image2);
		final Button next = (Button) findViewById(R.id.next);
		// 定义查看下一张图片的监听器
		next.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// 控制ImageView显示下一张图片
				image1.setImageResource(images[++currentImg % images.length]);
			}
		});
		// 定义改变图片透明度的方法
		OnClickListener listener = new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (v == plus) {
					alpha += 20;
				}
				if (v == minus) {
					alpha -= 20;
				}
				if (alpha >= 255) {
					alpha = 255;
				}
				if (alpha <= 0) {
					alpha = 0;
				}
				// 改变图片的透明度
				image1.setAlpha(alpha);
			}
		};
		// 为两个按钮添加监听器
		plus.setOnClickListener(listener);
		minus.setOnClickListener(listener);
		image1.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View view, MotionEvent event) {
				BitmapDrawable bitmapDrawable = (BitmapDrawable) image1.getDrawable();
				// 获取第一个图片显示框中的位图
				Bitmap bitmap = bitmapDrawable.getBitmap();
				// bitmap图片实际大小与第一个ImageView的缩放比例
				double scale = bitmap.getWidth() / 320.0;
				// 获取需要显示的图片的开始点
				int x = (int) (event.getX() * scale);
				int y = (int) (event.getY() * scale);
				if (x + 120 > bitmap.getWidth()) {
					x = bitmap.getWidth() - 120;
				}
				if (y + 120 > bitmap.getHeight()) {
					y = bitmap.getHeight() - 120;
				}
				// 显示图片的指定区域
				image2.setImageBitmap(Bitmap.createBitmap(bitmap, x, y, 120, 120));
				image2.setAlpha(alpha);
				return false;
			}
		});
	}
}
