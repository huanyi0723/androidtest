# 状态开发按钮

# 属性
# ToggleButton
- android:checked //是否被选中
- android:textOff //关闭时显示文字
- android:textOn //打开时显示文字

# Switch
- android:checked //是否被选中
- android:textOff //关闭时显示文字
- android:textOn //打开时显示文字
- android:switchMinWidth //开关最小宽度
- android:switchPadding //开关与文本间距
- android:textStyle //文本风格
- android:typeface //字体风格
- android:thumb //开关按钮
- android:track //开关轨道


<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="fill_parent"
    android:layout_height="fill_parent"
    android:orientation="vertical" >

    <!-- 定义一个ToggleButton按钮 -->

    <ToggleButton
        android:id="@+id/toggle"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:checked="true"
        android:textOff="横向排列"
        android:textOn="纵向排列" />

    <Switch
        android:id="@+id/switcher"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:checked="true"
        android:textOff="横向排列"
        android:textOn="纵向排列"
        android:thumb="@drawable/check" />
    <!-- 定义一个可以动态改变方向的线性布局 -->

    <LinearLayout
        android:id="@+id/test"
        android:layout_width="fill_parent"
        android:layout_height="fill_parent"
        android:orientation="vertical" >

        <Button
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="测试按钮一" />

        <Button
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="测试按钮二" />

        <Button
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="测试按钮三" />
    </LinearLayout>

</LinearLayout>

		final LinearLayout test = (LinearLayout) findViewById(R.id.test);
		OnCheckedChangeListener listener = new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton button, boolean isChecked) {
				if (isChecked) {
					// 设置LinearLayout垂直布局
					test.setOrientation(1);
				} else {
					// 设置LinearLayout水平布局
					test.setOrientation(0);
				}
			}
		};
		toggle.setOnCheckedChangeListener(listener);
		switcher.setOnCheckedChangeListener(listener);