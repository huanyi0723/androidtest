package com.example.systeminformationtest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		setContentView(R.layout.activity_main);
		TextView info = (TextView) findViewById(R.id.info);
		String phoneInfo = "Product: " + android.os.Build.PRODUCT + "\n";
        phoneInfo += "CPU_ABI: " + android.os.Build.CPU_ABI + "\n";
        phoneInfo += "TAGS: " + android.os.Build.TAGS + "\n";
        phoneInfo += "VERSION_CODES.BASE: " + android.os.Build.VERSION_CODES.BASE + "\n";
        phoneInfo += "MODEL: " + android.os.Build.MODEL + "\n";
        phoneInfo += "SDK: " + android.os.Build.VERSION.SDK + "\n";
        phoneInfo += "VERSION.RELEASE: " + android.os.Build.VERSION.RELEASE + "\n";
        phoneInfo += "DEVICE: " + android.os.Build.DEVICE + "\n";
        phoneInfo += "DISPLAY: " + android.os.Build.DISPLAY + "\n";
        phoneInfo += "BRAND: " + android.os.Build.BRAND + "\n";
        phoneInfo += "BOARD: " + android.os.Build.BOARD + "\n";
        phoneInfo += "FINGERPRINT: " + android.os.Build.FINGERPRINT + "\n";
        phoneInfo += "ID: " + android.os.Build.ID + "\n";
        phoneInfo += "MANUFACTURER: " + android.os.Build.MANUFACTURER + "\n";
        phoneInfo += "USER: " + android.os.Build.USER + "\n";
        
        info.setText(phoneInfo);
        
        Button more = (Button) findViewById(R.id.more);
        more.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				startActivity(new Intent(MainActivity.this, TelManager.class));
			}
		});
        
        Button bnmore = (Button) findViewById(R.id.bnmore);
        bnmore.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				startActivity(new Intent(MainActivity.this, MoreActivity.class));
			}
		});
	}

	
}
