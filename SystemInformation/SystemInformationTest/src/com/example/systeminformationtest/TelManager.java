package com.example.systeminformationtest;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.widget.TextView;

public class TelManager extends Activity {

	private TextView mTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tel_main);
		init();
	}

	private void init() {
		mTextView = (TextView) findViewById(R.id.textView);
		mTextView.setText(getHandSetInfo());
	}

	private String getHandSetInfo() {
		String handSetInfo = "手机型号:" + android.os.Build.MODEL + "\n" + "SDK版本:" + android.os.Build.VERSION.SDK + "\n" + "系统版本:"
				+ android.os.Build.VERSION.RELEASE + "\n" + "软件版本:" + getAppVersionName(TelManager.this);
		return handSetInfo;
	}

	// 获取当前版本号
	private String getAppVersionName(Context context) {
		String versionName = "";
		try {
			PackageManager packageManager = context.getPackageManager();
			PackageInfo packageInfo = packageManager.getPackageInfo("cn.testgethandsetinfo", 0);
			versionName = packageInfo.versionName;
			if (TextUtils.isEmpty(versionName)) {
				return "";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return versionName;
	}

}
