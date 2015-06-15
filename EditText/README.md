# 文本框

# 属性
- android:textCursorDrawable="@null" //修改EditText的光标颜色 让光标颜色和text color一样
- android:inputType //输入类型
- android:selectAllOnFocus 


"@null"   是作用是

<?xml version="1.0" encoding="utf-8"?>
<TableLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="fill_parent"
    android:layout_height="fill_parent"
    android:orientation="vertical"
    android:stretchColumns="1" >

    <TableRow>

        <TextView
            android:layout_width="fill_parent"
            android:layout_height="wrap_content"
            android:text="用户名:"
            android:textSize="16sp" />

        <EditText
            android:layout_width="fill_parent"
            android:layout_height="wrap_content"
            android:hint="请填写登录帐号"
            android:selectAllOnFocus="true" />
    </TableRow>

    <TableRow>

        <TextView
            android:layout_width="fill_parent"
            android:layout_height="wrap_content"
            android:text="密码:"
            android:textSize="16sp" />
        <!-- android:inputType="numberPassword"表明只能接收数字密码 -->

        <EditText
            android:layout_width="fill_parent"
            android:layout_height="wrap_content"
            android:inputType="numberPassword" />
    </TableRow>

    <TableRow>

        <TextView
            android:layout_width="fill_parent"
            android:layout_height="wrap_content"
            android:text="年龄："
            android:textSize="16sp" />
        <!-- inputType="number"表明是数值输入框 -->

        <EditText
            android:layout_width="fill_parent"
            android:layout_height="wrap_content"
            android:inputType="number" />
    </TableRow>

    <TableRow>

        <TextView
            android:layout_width="fill_parent"
            android:layout_height="wrap_content"
            android:text="生日："
            android:textSize="16sp" />
        <!-- inputType="date"表明是日期输入框 -->

        <EditText
            android:layout_width="fill_parent"
            android:layout_height="wrap_content"
            android:inputType="date" />
    </TableRow>

    <TableRow>

        <TextView
            android:layout_width="fill_parent"
            android:layout_height="wrap_content"
            android:text="电话号码:"
            android:textSize="16sp" />
        <!-- inputType="phone"表明是输入电话号码的输入框 -->

        <EditText
            android:layout_width="fill_parent"
            android:layout_height="wrap_content"
            android:hint="请填写您的电话号码"
            android:inputType="phone"
            android:selectAllOnFocus="true" />
    </TableRow>

    <Button
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="注册" />

</TableLayout>


# Android 手动显示和隐藏软键盘 

---java
//方法一(如果输入法在窗口上已经显示，则隐藏，反之则显示)
    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);  
    imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);  
//方法二（view为接受软键盘输入的视图，SHOW_FORCED表示强制显示）
InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
imm.showSoftInput(view,InputMethodManager.SHOW_FORCED);
//调用隐藏系统默认的输入法
(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(WidgetSearchActivity.this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);  
//(WidgetSearchActivity是当前的Activity)
获取输入法打开的状态
InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
boolean isOpen=imm.isActive();//isOpen若返回true，则表示输入法打开
---

# 换行符
控件
<1>如果直接在XML文件中写入"aaaaa\nbbbb"可以换行。
<2>在Java文件中用textViewObj.setText("aaaa\nbbbb")也可以换行。
<3>将数据封装到模型类后，在Java文件中使用textViewObj.setText(obj.getXXX()).

# ANDROID中EDITTEXT如何定位光标位置 
//			comment_input.setText("@" + comment_author + "\n");
//			int size = comment_input.getText().length();
//			comment_input.setSelection(size);

