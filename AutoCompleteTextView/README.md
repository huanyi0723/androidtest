# 自动完成文本框

public class AutoCompleteTextViewTest extends Activity {
	AutoCompleteTextView actv;
	MultiAutoCompleteTextView mauto;
	String[] books = new String[] { "测试1", "测试2", "测试3", "测试4" };

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		ArrayAdapter<String> aa = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, books);
		actv = (AutoCompleteTextView) findViewById(R.id.auto);
		actv.setAdapter(aa);
		
		mauto = (MultiAutoCompleteTextView) findViewById(R.id.mauto);
		mauto.setAdapter(aa);
		// 为MultiAutoCompleteTextView设置分隔符
		mauto.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
	}
}

<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="fill_parent"
    android:layout_height="fill_parent"
    android:orientation="vertical" >

    <!--
     定义一个自动完成文本框
	，指定输入一个字符后进行提示
    -->

    <AutoCompleteTextView
        android:id="@+id/auto"
        android:layout_width="fill_parent"
        android:layout_height="wrap_content"
        android:completionHint="请选择您喜欢的图书："
        android:completionThreshold="1"
        android:dropDownHorizontalOffset="10dp" />
    <!-- 定义一个MultiAutoCompleteTextView组件 -->

    <MultiAutoCompleteTextView
        android:id="@+id/mauto"
        android:layout_width="fill_parent"
        android:layout_height="wrap_content"
        android:completionThreshold="1" />

</LinearLayout>

