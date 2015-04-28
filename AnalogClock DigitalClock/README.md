# 时钟

# 属性
# AnalogClock
- android:dial //表盘图片
- android:hand_hour //时针图片
- android:hand_minute //分针图片

<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="fill_parent"
    android:layout_height="fill_parent"
    android:gravity="center_horizontal"
    android:orientation="vertical" >

    <!-- 定义模拟时钟 -->

    <AnalogClock
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" />
    <!-- 定义数字时钟 -->

    <DigitalClock
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:drawableRight="@drawable/ic_launcher"
        android:textColor="#f0f"
        android:textSize="14pt" />
    <!-- 定义模拟时钟,并使用自定义表盘、时针图片 -->

    <AnalogClock
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:dial="@drawable/watch"
        android:hand_minute="@drawable/hand" />

</LinearLayout>