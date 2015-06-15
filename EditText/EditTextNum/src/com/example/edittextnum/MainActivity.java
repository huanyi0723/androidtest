package com.example.edittextnum;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	int num = 10;// 限制的最大字数
	EditText et_content;// 定义一个文本输入框
	TextView tv_num;// 用来显示剩余字数

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		et_content = (EditText) findViewById(R.id.et_content);
		tv_num = (TextView) findViewById(R.id.tv_num);
		tv_num.setText("10");
		

		et_content.addTextChangedListener(new TextWatcher() {
			private CharSequence temp;
			private int selectionStart;
			private int selectionEnd;

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				temp = s;
				System.out.println("s=" + s);
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
				int number = num - s.length();
				tv_num.setText("" + number);
				selectionStart = et_content.getSelectionStart();
				selectionEnd = et_content.getSelectionEnd();
				// System.out.println("start="+selectionStart+",end="+selectionEnd);
				if (temp.length() > num) {
					s.delete(selectionStart - 1, selectionEnd);
					int tempSelection = selectionStart;
					et_content.setText(s);
					et_content.setSelection(tempSelection);// 设置光标在最后
				}
			}
		});
	}

}
