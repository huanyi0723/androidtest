# 绝对布局
- 不建议使用

# 属性
- android:layout_x //该组件X坐标
- android:layout_y //该组件Y坐标

<?xml version="1.0" encoding="utf-8"?>
<AbsoluteLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="fill_parent"
    android:layout_height="fill_parent"
    android:orientation="vertical" >

    <!-- 定义一个文本框，使用绝对定位 -->

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_x="20dip"
        android:layout_y="20dip"
        android:text="用户名：" />
    <!-- 定义一个文本编辑框，使用绝对定位 -->

    <EditText
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_x="80dip"
        android:layout_y="15dip"
        android:width="200px" />
    <!-- 定义一个文本框，使用绝对定位 -->

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_x="20dip"
        android:layout_y="80dip"
        android:text="密  码：" />
    <!-- 定义一个文本编辑框，使用绝对定位 -->

    <EditText
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_x="80dip"
        android:layout_y="75dip"
        android:password="true"
        android:width="200px" />
    <!-- 定义一个按钮，使用绝对定位 -->

    <Button
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_x="130dip"
        android:layout_y="135dip"
        android:text="登   录" />

</AbsoluteLayout>