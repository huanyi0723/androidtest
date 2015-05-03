package com.example.floatviewdemo;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RemoteViews;
import android.widget.TextView;

public class FloatService extends Service implements OnClickListener{
    private WindowManager mWindowManager;
    private View view;
    
    private Button mmBtn;
    private CheckBox mChk;
    private ImageView mImg;
    private TextView mText;
    private String stopTime = "00:00:00";
    
    private Button mBtn;
    
    private long clickTime;
    private long time;
    public WindowManager.LayoutParams mParams;
    
    private FloatView mFloatView;
    
    private DisplayMetrics dm;
    
    private Notification notification;
    private NotificationManager nw;
    
    private String START = "start";
    private String STOP = "stop";
    
    private Timer mFloatTimer;
    private Handler mFloatHandler = new Handler(){
        public void handleMessage(Message msg) {
            if(msg.what == 1){
                time = System.currentTimeMillis() - clickTime;
                mText.setText(refreshTime());
                mWindowManager.updateViewLayout(view, mParams);
            }
        };
    };

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("onCreate");
        dm = new DisplayMetrics();
        dm = getResources().getDisplayMetrics();
        initWindow(getApplicationContext());
        showNotification();
        
        IntentFilter filter = new IntentFilter();
        
        filter.addAction(START);
        filter.addAction(STOP);
        registerReceiver(receiver, filter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
    
    
    
    public void showNotification(){
        
        nw = (NotificationManager) getApplicationContext().getSystemService(NOTIFICATION_SERVICE);
        
        notification = new Notification();
        notification.icon = R.drawable.ic_launcher;
        notification.tickerText = getResources().getText(R.string.app_name);
        
        
        Intent statusIntent = new Intent();
        statusIntent.setAction(START);
//        statusIntent.setAction(STOP);
        PendingIntent statusPendingIntent = PendingIntent.getBroadcast(this, 0, statusIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        
        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.notification_layout);
        
        remoteViews.setImageViewResource(R.id.notification_icon, R.drawable.launcher_icon);
        remoteViews.setTextViewText(R.id.notification_title_tips, "我是notification title tips");
        remoteViews.setTextViewText(R.id.notification_body_tips, "我是notification body tips");
//        remoteViews.setImageViewResource(R.id.notification_audio_status, R.drawable.notification_next);
//        remoteViews.
        
        if(!mFloatView.getStatus()){
            remoteViews.setImageViewResource(R.id.notification_status, R.drawable.notification_play);
        } else {
            remoteViews.setImageViewResource(R.id.notification_status, R.drawable.notification_pause);
        }
        remoteViews.setOnClickPendingIntent(R.id.notification_status, statusPendingIntent);
//        remoteViews.setOnClickPendingIntent(R.id.notification_audio_status, statusPendingIntent);
        
        
        notification.contentView = remoteViews;
        notification.contentIntent = statusPendingIntent;  
        nw.notify(0,notification);
    }
    
    public void initWindow(Context context){
        mWindowManager = (WindowManager) context.getSystemService("window");
//        view = LayoutInflater.from(context).inflate(R.layout.flaot_view, null);
//        mBtn = (Button) view.findViewById(R.id.m_btn);
//        mBtn.setOnClickListener(this);
//        mImg = (ImageView) view.findViewById(R.id.mm_img);
//        mImg.setBackgroundResource(R.drawable.rec_status_2);
//        mChk = (CheckBox) view.findViewById(R.id.mm_audio_check);
//        mmBtn = (Button) view.findViewById(R.id.mm_btn);
//        mmBtn.setOnClickListener(this);
        
//        mText = (TextView) view.findViewById(R.id.mm_tv);
//        mText.setText(stopTime);
        
        mFloatView = new FloatView(context);
        mParams = new WindowManager.LayoutParams();
        mParams.x = 10;
        mParams.y = 300;
        
        mParams.gravity = Gravity.LEFT | Gravity.TOP;
        mParams.type = 2002; // 2002为TYPE_PHONE
        mParams.format = 1;
        mParams.flags = 40;
        
        mParams.width = LayoutParams.WRAP_CONTENT;
        mParams.height = LayoutParams.WRAP_CONTENT;
        
        
        mFloatView.setP(mParams,dm.widthPixels,dm.heightPixels);
        mWindowManager.addView(mFloatView, mParams);
    }

    @Override
    public void onClick(View v) {
        
        
        if(v == mmBtn){
            clickTime = System.currentTimeMillis();
            
            mImg.setBackgroundResource(R.drawable.record_ani);
            mImg.post(mAnimationRunnable);
            
            mFloatTimer = new Timer();
            TimerTask task = new TimerTask() {
                
                @Override
                public void run() {
                    Message msg = new Message();
                    msg.what = 1;
                    mFloatHandler.sendMessage(msg);
                }
            };
            mFloatTimer.schedule(task, 0, 1000);
        } else {
            if(mText.isShown()){
                mText.setVisibility(View.GONE);
                mmBtn.setVisibility(View.GONE);
                mChk.setVisibility(View.GONE);
            } else {
                mText.setVisibility(View.VISIBLE);
                mmBtn.setVisibility(View.VISIBLE);
                mChk.setVisibility(View.VISIBLE);
            }
        }
    }
    
    public String refreshTime(){
        time = System.currentTimeMillis() - clickTime;
        System.out.println("clickTime :" + clickTime);
        System.out.println("time :" + time);
        String timerrrrr = formatDuration(time);
        System.out.println(timerrrrr);
        return timerrrrr;
     }
    
    public WindowManager.LayoutParams getLayoutParams(){
        return mParams;
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

    private final Runnable mAnimationRunnable = new Runnable() {
        @Override
        public void run() {
            Drawable bg = mImg.getBackground();
            if (bg != null && (bg instanceof AnimationDrawable)) {
                ((AnimationDrawable) bg).start();
            }
        }
    };
    
    public BroadcastReceiver receiver = new BroadcastReceiver(){
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if(START.equals(action)){
                if(!mFloatView.getStatus()){
                    mFloatView.startRecord();
                } else {
                    mFloatView.endRecord();
                }
                showNotification();
            }
            
        };
    };
    

}
