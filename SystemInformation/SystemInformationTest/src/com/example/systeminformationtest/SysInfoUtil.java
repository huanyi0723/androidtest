package com.example.systeminformationtest;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

public class SysInfoUtil {

	// 获取手机型号
	public static String getSystemModle() {
		return android.os.Build.MODEL;
	}

	// 获取SDK版本
	public static String getSdkVersion() {
		return android.os.Build.VERSION.SDK;
	}

	// 获取系统版本
	public static String getSystemVersion() {
		return android.os.Build.VERSION.RELEASE;
	}

	// 获取软件版本
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

	// 获取设备号
	public static String getDeviceId(Context context) {
		TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		return tm.getDeviceId();
	}
}
