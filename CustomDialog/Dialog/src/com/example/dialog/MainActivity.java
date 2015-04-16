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
		//去掉默认的背景,下面两个都可以
		dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.R.color.transparent));
		//dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
		//http://stackoverflow.com/questions/12348405/dialog-is-bigger-than-expected-when-using-relativelayout
		//dialog默认都是有title的
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题，否则会影响高度计算，一定要在setContentView之前调用，终于明白有一个设置theme的构造函数的目的了
		dialog.setContentView(rootView);
	
		
		dialog.show();
	}
	

	

	
}
