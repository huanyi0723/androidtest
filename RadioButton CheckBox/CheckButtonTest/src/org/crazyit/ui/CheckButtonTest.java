package org.crazyit.ui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class CheckButtonTest extends Activity {
	RadioGroup rg;
	TextView show;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		rg = (RadioGroup) findViewById(R.id.rg);
		show = (TextView) findViewById(R.id.show);
		
		rg.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// 根据用户勾选的单选按钮来动态改变tip字符串的值
				String tip = checkedId == R.id.male ? "您的性别是男人" : "您的性别是女人";
				// 修改show组件中的文本。
				show.setText(tip);
			}
		});
	}
}