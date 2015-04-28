- 单选按钮 复选框


<?xml version="1.0" encoding="utf-8"?>
<TableLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="fill_parent"
    android:layout_height="fill_parent"
    android:orientation="vertical" >

    <TableRow>

        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="性别:"
            android:textSize="16px" />
        <!-- 定义一组单选框 -->

        <RadioGroup
            android:id="@+id/rg"
            android:layout_gravity="center_horizontal"
            android:orientation="horizontal" >

            <!-- 定义两个单选框 -->

            <RadioButton
                android:id="@+id/male"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:checked="true"
                android:text="男" />

            <RadioButton
                android:id="@+id/female"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="女" />
        </RadioGroup>
    </TableRow>

    <TableRow>

        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="喜欢的颜色:"
            android:textSize="16px" />
        <!-- 定义一个垂直的线性布局 -->

        <LinearLayout
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_gravity="center_horizontal"
            android:orientation="vertical" >

            <!-- 定义三个复选框 -->

            <CheckBox
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:checked="true"
                android:text="红色" />

            <CheckBox
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="蓝色" />

            <CheckBox
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="绿色" />
        </LinearLayout>
    </TableRow>

    <TextView
        android:id="@+id/show"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" />

</TableLayout>

		rg.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// 根据用户勾选的单选按钮来动态改变tip字符串的值
				String tip = checkedId == R.id.male ? "您的性别是男人" : "您的性别是女人";
				// 修改show组件中的文本。
				show.setText(tip);
			}
		});