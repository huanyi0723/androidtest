package com.example.systeminformationtest;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

public class SysInfoUtil {

	// ��ȡ�ֻ��ͺ�
	public static String getSystemModle() {
		return android.os.Build.MODEL;
	}

	// ��ȡSDK�汾
	public static String getSdkVersion() {
		return android.os.Build.VERSION.SDK;
	}

	// ��ȡϵͳ�汾
	public static String getSystemVersion() {
		return android.os.Build.VERSION.RELEASE;
	}

	// ��ȡ����汾
	public static String getAppVersionName(Context context) {
		String versionName = "";
		try {
			PackageManager packageManager = context.getPackageManager();
			PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
			versionName = packageInfo.versionName;
			if (TextUtils.isEmpty(versionName)) {
				return "";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return versionName;
	}

	// ��ȡ�豸��
	public static String getDeviceId(Context context) {
		TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		return tm.getDeviceId();
	}
}
