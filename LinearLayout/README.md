- 线性布局
属性
- android:orientation //horizontal水平 vertical垂直
- android:gravity //对齐方式
- android:divider //按钮之间分割条
- 很少用的 android:baselineAligned android:measureWithLargestChild

<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="fill_parent"
    android:layout_height="fill_parent"
    android:gravity="top"
    android:orientation="horizontal" >

    <Button
        android:id="@+id/bn1"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="@string/bn1" />

    <Button
        android:id="@+id/bn2"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="@string/bn2" />

    <Button
        android:id="@+id/bn3"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="@string/bn3" />

    <Button
        android:id="@+id/bn4"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="@string/bn4" />

    <Button
        android:id="@+id/bn5"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="@string/bn5" />

</LinearLayout>