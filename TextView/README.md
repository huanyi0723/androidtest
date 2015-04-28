# 文本框
- CheckedTextView

# 大量属性
- android:autoLink //文本转超链接
- android:autoText //URL E-mail 自动转链接
- android:capitalize //用户输入转大写字母
- android:cursorVisible //文本框光标是否可见
- android:digits //输入法为数字
- android:drawableTop //文本框上部绘图
- android:drawableBottom //文本框底部绘图
- android:drawableLeft //文本框左部绘图
- android:drawableRight //文本框右部绘图
- android:drawableStart //文本框开始处绘图
- android:drawableEnd //文本框结束处绘图
- android:drawablePadding //文本与图形间距
- android:editable //是否可编辑
- android:ellipsize //文本过长如何处理
- android:ems //设置组件宽度
- android:fontFamily //文本框内文本字体
- android:hint //文字提示
- android:imeActionId //输入法
- android:imeActionLabel //输入法
- android:imeOptions //输入法
- android:includeFontPadding //是否为字体保留足够空间
- android:inputMethod //输入法
- android:inputType //文本框类型
- android:lineSpacingExtra //两行文本额外间距
- android:lineSpacingMultiplier //两行文本额外间距
- android:lines //默认几行
- android:linksClickable //超链接是否可点击
- android:marqueeRepeatLimit //marquee动画
- android:maxEms //最大宽度 
- android:maxHeight //最大高度
- android:maxWidth //最大宽度
- android:maxLength //最大字符长度
- android:maxLines //最多占几行
- android:minEms //最小宽度
- android:minHeight //最小高度
- android:minWidth //最小宽度
- android:minLines //最少占几行
- android:numeric //数值输入法
- android:password //密码框
- android:phoneNumber //电话号码
- android:privateImeOptions
- android:scrollHorizontally //文字太多是否可水平滚动
- android:selectAllOnFocus //焦点 选择
- android:shadowColor //文本阴影颜色
- android:shadowDx //文本阴影水平偏移
- android:shadowDy //文本阴影垂直偏移
- android:shadowRadius //文本阴影模糊程度
- android:singleLine //是否单行显示
- 
- android:text //文本内容
- android:textAllCaps //大写字母
- android:textAppearance //样式
- android:textColor //文本颜色
- android:textColorHighlight //文本被选中颜色
- android:textColorHint //提示文本颜色
- android:textColorLink //链接颜色
- android:textIsSelectable //文本不能编辑 是否可选中
- android:textScaleX //文本水平缩放因子
- android:textSize //字体大小
- android:textStyle //字体风格
- android:typeface //字体风格

<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="fill_parent"
    android:layout_height="fill_parent"
    android:orientation="vertical" >

    <!-- 设置字体为20pt，文本框结尾处绘制图片 -->

    <TextView
        android:layout_width="fill_parent"
        android:layout_height="wrap_content"
        android:drawableEnd="@drawable/ic_launcher"
        android:text="我爱Java"
        android:textSize="20pt" />
    <!-- 设置中间省略， 所有字母大写 -->

    <TextView
        android:layout_width="fill_parent"
        android:layout_height="wrap_content"
        android:ellipsize="middle"
        android:singleLine="true"
        android:text="我爱Java我爱Java我爱Java我爱Java我爱Java我aaaJava"
        android:textAllCaps="true" />
    <!-- 对邮件、电话增加链接 -->

    <TextView
        android:layout_width="fill_parent"
        android:layout_height="wrap_content"
        android:autoLink="email|phone"
        android:singleLine="true"
        android:text="邮件是kongyeeku@163.com，电话是02088888888" />
    <!-- 设置文字颜色 、大小，并使用阴影 -->

    <TextView
        android:layout_width="fill_parent"
        android:layout_height="wrap_content"
        android:shadowColor="#0000ff"
        android:shadowDx="10.0"
        android:shadowDy="8.0"
        android:shadowRadius="3.0"
        android:text="测试文字"
        android:textColor="#f00"
        android:textSize="18pt" />
    <!-- 测试密码框 -->

    <TextView
        android:id="@+id/passwd"
        android:layout_width="fill_parent"
        android:layout_height="wrap_content"
        android:password="true"
        android:text="@string/hello" />
    <!--
    测试CheckedTextView
	通过checkMark设置该文本框的勾选图标
    -->

    <CheckedTextView
        android:layout_width="fill_parent"
        android:layout_height="wrap_content"
        android:checkMark="@drawable/ok"
        android:text="可勾选的文本" />

</LinearLayout>