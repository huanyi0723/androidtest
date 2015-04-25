- 等待框代码

private ProgressBar bar;// 等待框

bar = (ProgressBar) findViewById(R.id.bar);

//显示
bar.setVisibility(View.VISIBLE);

//隐藏
bar.setVisibility(View.GONE);

	<ProgressBar
            android:id="@+id/bar"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_centerInParent="true"
            android:indeterminateDrawable="@drawable/zy_rotate_loading_github"
            android:indeterminateDuration="1800"
            android:visibility="gone" />