package org.crazyit.ui;

import android.app.Activity;
import android.os.Bundle;

public class RelativeLayoutTest extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		findViewById(R.id.view01).setPadding(5, 5, 5, 5);
		findViewById(R.id.view02).setPadding(5, 5, 5, 5);
		findViewById(R.id.view03).setPadding(5, 5, 5, 5);
		findViewById(R.id.view04).setPadding(5, 5, 5, 5);
		findViewById(R.id.view05).setPadding(5, 5, 5, 5);
	}
}