package org.crazyit.ui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;

public class AutoCompleteTextViewTest extends Activity {
	AutoCompleteTextView actv;
	MultiAutoCompleteTextView mauto;
	String[] books = new String[] { "≤‚ ‘1", "≤‚ ‘2", "≤‚ ‘3", "≤‚ ‘4" };

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		ArrayAdapter<String> aa = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, books);
		actv = (AutoCompleteTextView) findViewById(R.id.auto);
		actv.setAdapter(aa);
		
		mauto = (MultiAutoCompleteTextView) findViewById(R.id.mauto);
		mauto.setAdapter(aa);
		// Œ™MultiAutoCompleteTextView…Ë÷√∑÷∏Ù∑˚
		mauto.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
	}
}