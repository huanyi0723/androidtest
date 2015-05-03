package com.example.floatviewdemo;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.graphics.PointF;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FloatView extends LinearLayout implements OnClickListener{
    
    private Button mBtn;
    private TextView mTv;
    private ImageView mImg;
    private View view;
    
    private PointF start = new PointF();
    private float x;
    private float y;
    private float currX;
    private float currY;
    
    private int width;
    private int height;
    
    float offsetX;
    float offsetY;
    
    private WindowManager manager;
    private WindowManager.LayoutParams mParams;
    
    private String stopTime = "00:00:00";
    
    private long clickTime;
    private long currentTime;
    
    private Timer timer;
    
    private boolean isStart = false;
    
    private Handler handler = new Handler(){
        public void handleMessage(android.os.Message msg) {
            if(msg.what == 1){
                currentTime = System.currentTimeMillis() - clickTime;
                mTv.setText(formatDuration(currentTime));
                manager.updateViewLayout(FloatView.this, mParams);
            }
        };
    };
    
    public FloatView(Context context) {
        super(context);
//        view = LayoutInflater.from(context).inflate(R.layout.flaot_view, null);
//        mBtn = (Button) view.findViewById(R.id.mm_btn);
//        mTv = (TextView) view.findViewById(R.id.mm_tv);
//        mImg = (ImageView) view.findViewById(R.id.mm_img);
        
        manager = (WindowManager) context.getSystemService("window");
        this.setOrientation(HORIZONTAL);
        mBtn = new Button(context);
        mTv = new TextView(context);
        mImg = new ImageView(context);
        mImg.setBackgroundResource(R.drawable.rec_status_1);
        mTv.setText(stopTime);
        mBtn.setOnClickListener(this);
        this.addView(mBtn);
        this.addView(mTv);
        this.addView(mImg);
    }

    public void setData(String string){
        mTv.setText(string);
    }
    
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        x = event.getRawX();
        y = event.getRawY() - 25; // 25是系统状态栏的高度
        
        switch(event.getAction()){
        case MotionEvent.ACTION_DOWN:
            currX = event.getX();
            currY = event.getY();
            start.set(event.getX(), event.getY());
            break;
        case MotionEvent.ACTION_MOVE:
            mParams.x = (int)(x - currX);
            mParams.y = (int)(y - currY);
            manager.updateViewLayout(this, mParams);
            
            offsetX = event.getX() - start.x;
            offsetY = event.getY() - start.y;
            break;
        case MotionEvent.ACTION_UP:
//            
            if((this.x) <= (float)(width/2 + 100)){
                mParams.x = 0;
                manager.updateViewLayout(this, mParams);
            } else {
                mParams.x = width;
                manager.updateViewLayout(this, mParams);
            }
            
            
            currX = 0;
            currY = 0;
            break;
        }
        
        return true;
    }
    
    public void setP(WindowManager.LayoutParams mParams,int width, int height){
        this.mParams = mParams;
        this.width = width;
        this.height = height;
    }
    
    @Override
    public void onClick(View v) {
        if(v == mBtn){
            if(mTv.getText().toString().equals(stopTime)){
                startRecord();
            } else {
                endRecord();
            }
        }
    }

    public void startRecord() {
        isStart = true;
        clickTime = System.currentTimeMillis();
        startTimer();
        mImg.setBackgroundResource(R.drawable.record_ani);
        mImg.post(mAnimationRunnable);
    }

    public void endRecord() {
        isStart = false;
        if(timer != null){
            timer.cancel();
        }
        mImg.setBackgroundResource(R.drawable.rec_status_1);
        mTv.setText(stopTime);
    }
    
    public boolean getStatus(){
        return isStart;
    }
    
    private final Runnable mAnimationRunnable = new Runnable() {
        @Override
        public void run() {
            Drawable bg = mImg.getBackground();
            if (bg != null && (bg instanceof AnimationDrawable)) {
                ((AnimationDrawable) bg).start();
            }
        }
    };
    
    private void startTimer(){
        timer = new Timer();
        TimerTask task = new TimerTask() {
            
            @Override
            public void run() {
                Message msg = new Message();
                msg.what = 1;
                handler.sendMessage(msg);
            }
        };
        timer.schedule(task, 0, 1000);
    }
    
    /**
     * 持续时间格式
     * 
     * @param duration
     *            持续时间
     * @param hourSign
     *            小时
     * @param minuteSign
     *            分钟
     * @return
     */
    public static String formatDuration(long duration) {
        duration /= 1000L;
        int hour = (int) (duration / 3600L);
        int minute = (int) ((duration % 3600L) / 60);
        int second = (int)((duration  % 3600L) % 60);
        return String.format("%02d:%02d:%02d", hour, minute ,second);
    }
}
