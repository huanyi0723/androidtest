package com.example.floatviewdemo;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener{
    
    private Button mBtn;

    private NotificationManager nm;
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        startService(new Intent(MainActivity.this,FloatService.class));
        
        
        mBtn = (Button) findViewById(R.id.show_float_view);
        mBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        nm = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        nm.cancel(0);
    }
}
