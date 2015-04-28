# 计时器

<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="fill_parent"
    android:layout_height="fill_parent"
    android:gravity="center_horizontal"
    android:orientation="vertical" >

    <Chronometer
        android:id="@+id/test"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:textColor="#ffff0000"
        android:textSize="12pt" />

    <Button
        android:id="@+id/start"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="启动" />

</LinearLayout>

		ch = (Chronometer) findViewById(R.id.test);
		start = (Button) findViewById(R.id.start);
		
		start.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View source) {
				ch.setBase(SystemClock.elapsedRealtime()); // 设置开始计时时间
				ch.start(); // 启动计时器
				start.setEnabled(false);
			}
		});
		
		ch.setOnChronometerTickListener(new OnChronometerTickListener() {
			@Override
			public void onChronometerTick(Chronometer ch) {
				// 如果从开始计时到现在超过了20s。
				if (SystemClock.elapsedRealtime() - ch.getBase() > 20 * 1000) {
					ch.stop();
					start.setEnabled(true);
				}
			}
		});