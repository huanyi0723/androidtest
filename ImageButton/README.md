# ImageButton

# ZoomButton

# ZoomControls



<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="fill_parent"
    android:layout_height="fill_parent"
    android:orientation="vertical" >

    <!-- 普通图片按钮 -->

    <ImageButton
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:src="@drawable/blue" />
    <!-- 按下时显示不同图片的按钮 -->

    <ImageButton
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:src="@drawable/button_selector" />

    <LinearLayout
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="center_horizontal"
        android:layout_margin="10sp"
        android:orientation="horizontal" >

        <!-- 分别定义2个ZoomButton，并分别似乎用btn_minus和btn_plus图片 -->

        <ZoomButton
            android:id="@+id/btn_zoom_down"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:src="@android:drawable/btn_minus" />

        <ZoomButton
            android:id="@+id/btn_zoom_up"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:src="@android:drawable/btn_plus" />
    </LinearLayout>
    <!-- 定义ZoomControls组件 -->

    <ZoomControls
        android:id="@+id/zoomControls1"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="center_horizontal" />

</LinearLayout>