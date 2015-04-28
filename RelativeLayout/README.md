# 帧布局
- 组件一个个叠加上去

# 属性
- android:gravity 
- android:ignoreGravity //设置哪个组件不受gravity影响
- android:layout_centerHorizontal //水平居中
- android:layout_centerVertical //垂直居中
- android:layout_centerInParent //完全居中
- android:layout_alignParentLeft //容器左边
- android:layout_alignParentRight //容器右边
- android:layout_alignParentTop //容器上端
- android:layout_alignParentBottom //容器下端
-
- android:layout_toLeftOf //某控件左边
- android:layout_toRightOf //某控件右边
- android:layout_above //某控件上面
- android:layout_below //某控件下面
- android:layout_alignLeft //与某控件左边界对齐
- android:layout_alignRight //与某控件右边界对齐
- android:layout_alignTop //与某控件上边界对齐
- android:layout_alignBottom //与某控件下边界对齐

<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="fill_parent"
    android:layout_height="fill_parent" >

    <!-- 定义该组件位于父容器中间 -->

    <TextView
        android:id="@+id/view01"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_centerInParent="true"
        android:background="@drawable/leaf" />
    <!-- 定义该组件位于view01组件的上方 -->

    <TextView
        android:id="@+id/view02"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_above="@id/view01"
        android:layout_alignLeft="@id/view01"
        android:background="@drawable/leaf" />
    <!-- 定义该组件位于view01组件的下方 -->

    <TextView
        android:id="@+id/view03"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_alignLeft="@id/view01"
        android:layout_below="@id/view01"
        android:background="@drawable/leaf" />
    <!-- 定义该组件位于view01组件的左边 -->

    <TextView
        android:id="@+id/view04"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_alignTop="@id/view01"
        android:layout_toLeftOf="@id/view01"
        android:background="@drawable/leaf" />
    <!-- 定义该组件位于view01组件的右边 -->

    <TextView
        android:id="@+id/view05"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_alignTop="@id/view01"
        android:layout_toRightOf="@id/view01"
        android:background="@drawable/leaf" />

</RelativeLayout>