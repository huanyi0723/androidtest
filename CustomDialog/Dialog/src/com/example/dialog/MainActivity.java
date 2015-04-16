package com.example.dialog;


import android.app.Activity;
import android.app.Dialog;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button bn = (Button)findViewById(R.id.bn);
		bn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				useDialog();
			}
		});
	
	}

	private void useDialog( ){
		LayoutInflater mInflater = LayoutInflater.from(this);
	 	ViewGroup rootView = (ViewGroup)mInflater.inflate(R.layout.menu, null);
	 	rootView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		Dialog dialog = new Dialog(this);
		WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
		params.width = WindowManager.LayoutParams.WRAP_CONTENT;
		params.height = WindowManager.LayoutParams.WRAP_CONTENT;
		//ȥ��Ĭ�ϵı���,��������������
		dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.R.color.transparent));
		//dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
		//http://stackoverflow.com/questions/12348405/dialog-is-bigger-than-expected-when-using-relativelayout
		//dialogĬ�϶�����title��
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);//ȥ�����⣬�����Ӱ��߶ȼ��㣬һ��Ҫ��setContentView֮ǰ���ã�����������һ������theme�Ĺ��캯����Ŀ����
		dialog.setContentView(rootView);
	
		
		dialog.show();
	}
	

	

	
}
