# 网格视图

# 属性
- android:columnWidth //列的宽度
- android:horizontalSpacing //水平间距
- android:numColumns //列数
- android:stretchMode //拉伸模式
- android:verticalSpacing //垂直间距

public class GridViewTest extends Activity {
	GridView grid;
	ImageView imageView;
	int[] imageIds = new int[] { R.drawable.bomb5, R.drawable.bomb6, R.drawable.bomb7, R.drawable.bomb8,
			R.drawable.bomb9, R.drawable.bomb10, R.drawable.bomb11, R.drawable.bomb12, R.drawable.bomb13,
			R.drawable.bomb14, R.drawable.bomb15, R.drawable.bomb16 };

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		// 创建一个List对象，List对象的元素是Map
		List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < imageIds.length; i++) {
			Map<String, Object> listItem = new HashMap<String, Object>();
			listItem.put("image", imageIds[i]);
			listItems.add(listItem);
		}
		// 获取显示图片的ImageView
		imageView = (ImageView) findViewById(R.id.imageView);

		SimpleAdapter simpleAdapter = new SimpleAdapter(this, listItems, R.layout.cell, new String[] { "image" }, new int[] { R.id.image1 });
		grid = (GridView) findViewById(R.id.grid01);
		// 为GridView设置Adapter
		grid.setAdapter(simpleAdapter);
		
		// 添加列表项被选中的监听器
		grid.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				// 显示当前被选中的图片
				imageView.setImageResource(imageIds[position]);
			}
			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});
		
		// 添加列表项被单击的监听器
		grid.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// 显示被单击的图片的图片
				imageView.setImageResource(imageIds[position]);
			}
		});
	}
}


<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="fill_parent"
    android:layout_height="fill_parent"
    android:gravity="center_horizontal"
    android:orientation="vertical" >

    <!-- 定义一个GridView组件 -->
    <GridView
        android:id="@+id/grid01"
        android:layout_width="fill_parent"
        android:layout_height="wrap_content"
        android:gravity="center"
        android:horizontalSpacing="1pt"
        android:numColumns="4"
        android:verticalSpacing="1pt" />
    
    <!-- 定义一个ImageView组件 -->
    <ImageView
        android:id="@+id/imageView"
        android:layout_width="240dp"
        android:layout_height="240dp"
        android:layout_gravity="center_horizontal" />

</LinearLayout>

