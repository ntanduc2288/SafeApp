package com.viewnine.safeapp.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PowerManager;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.viewnine.safeapp.glowpad.GlowPadView;
import com.viewnine.safeapp.service.MyService;
import com.viewnine.safeapp.ulti.ViewUlti;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class LockScreenAppActivity extends Activity {


    private GlowPadView glowPadView;
    private TextView lblTime, lblDate;
    private Calendar calendar;
    SimpleDateFormat sdf;

    @Override
    public void onAttachedToWindow() {
        // TODO Auto-generated method stub
//		this.getWindow().setType(WindowManager.LayoutParams.TYPE_KEYGUARD_DIALOG|WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onAttachedToWindow();
    }

//	  @Override 
//		public void onAttachedToWindow() { 
//		    super.onAttachedToWindow(); 
//		    this.getWindow().setType(WindowManager.LayoutParams.TYPE_KEYGUARD);           
//		} 
//		

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);


        setContentView(R.layout.lockscreen_view);

        initLockScreen();
        initGlowPad();
        initDateTime();


    }

    private void initGlowPad() {
        glowPadView = (GlowPadView) findViewById(R.id.glow_pad_view);
        glowPadView.setOnTriggerListener(new GlowPadView.OnTriggerListener() {
            @Override
            public void onGrabbed(View v, int handle) {

            }

            @Override
            public void onReleased(View v, int handle) {

            }

            @Override
            public void onTrigger(View v, int target) {
                Toast.makeText(LockScreenAppActivity.this, "Clicked on position: " + target, Toast.LENGTH_SHORT).show();
                switch (target) {
                    case 0:
                        turnOffScreen();
                        break;
                    default:
                        finish();
                        break;

                }
            }

            @Override
            public void onGrabbedStateChange(View v, int handle) {

            }

            @Override
            public void onFinishFinalAnimation() {

            }
        });

    }


    Handler mainhandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            calendar = Calendar.getInstance();

            sdf = new SimpleDateFormat("hh:mm a");
            String time = sdf.format(calendar.getTime());
            lblTime.setText(time);

            sdf = new SimpleDateFormat(" EE, d  MMM yyyy");
            String date = sdf.format(calendar.getTime());
            lblDate.setText(date);


        }
    };

    private void initDateTime() {
        lblTime = (TextView) findViewById(R.id.time);
        lblDate = (TextView) findViewById(R.id.date);

        calendar = Calendar.getInstance();
        sdf = new SimpleDateFormat("hh:mm a");
        String time = sdf.format(calendar.getTime());
        lblTime.setText(time);

        sdf = new SimpleDateFormat(" EE, d  MMM yyyy");
        String date = sdf.format(calendar.getTime());
        lblDate.setText(date);


        new Thread(new Runnable() {
            public void run() {

                while (true) {
                    try {

                        Thread.sleep(1000);
                        mainhandler.sendEmptyMessage(0);

                    } catch (InterruptedException e) {

                        e.printStackTrace();
                    }

                }

            }
        }).start();
    }

    private void turnOffScreen() {
        PowerManager manager = (PowerManager) getSystemService(Context.POWER_SERVICE);


// Choice 2
        PowerManager.WakeLock wl = manager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "Your Tag");
        wl.acquire();
        wl.release();
    }

    private void initLockScreen() {

        ViewUlti.hideNavigationBar(this);

        if (getIntent() != null && getIntent().hasExtra("kill") && getIntent().getExtras().getInt("kill") == 1) {
            // Toast.makeText(this, "" + "kill activityy", Toast.LENGTH_SHORT).show();
            finish();
        }

        try {
            // initialize receiver



            startService(new Intent(this, MyService.class));




  /*      KeyguardManager km =(KeyguardManager)getSystemService(KEYGUARD_SERVICE);
        k1 = km.newKeyguardLock("IN");
        k1.disableKeyguard();*/
            StateListener phoneStateListener = new StateListener();
            TelephonyManager telephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
            telephonyManager.listen(phoneStateListener, PhoneStateListener.LISTEN_CALL_STATE);


        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    class StateListener extends PhoneStateListener {
        @Override
        public void onCallStateChanged(int state, String incomingNumber) {

            super.onCallStateChanged(state, incomingNumber);
            switch (state) {
                case TelephonyManager.CALL_STATE_RINGING:
                    break;
                case TelephonyManager.CALL_STATE_OFFHOOK:
                    System.out.println("call Activity off hook");
                    finish();


                    break;
                case TelephonyManager.CALL_STATE_IDLE:
                    break;
            }
        }
    }

    ;


    @Override
    public void onBackPressed() {
        // Don't allow back to dismiss.
        return;
    }

    //only used in lockdown mode
    @Override
    protected void onPause() {
        super.onPause();

        // Don't hang around.
        // finish();
    }

    @Override
    protected void onStop() {
        super.onStop();

        // Don't hang around.
        // finish();
    }


//    @Override
//  public boolean onKeyDown(int keyCode, android.view.KeyEvent event) {
//
//    	if ((keyCode == KeyEvent.KEYCODE_VOLUME_DOWN)||(keyCode == KeyEvent.KEYCODE_POWER)||(keyCode == KeyEvent.KEYCODE_VOLUME_UP)||(keyCode == KeyEvent.KEYCODE_CAMERA)) {
//    	    //this is where I can do my stuff
//    	    return true; //because I handled the event
//    	}
//       if((keyCode == KeyEvent.KEYCODE_HOME)){
//
//    	   return true;
//        }
//
//	return false;
//
//    }

//	@Override 
//	public boolean onKeyDown(int keyCode, KeyEvent event) {
//	    if(keyCode == KeyEvent.KEYCODE_HOME)
//	    { 
//	        Log.i("Home Button","Clicked");
//	    } 
//	    if(keyCode==KeyEvent.KEYCODE_BACK)
//	    { 
//	        finish();
//	    } 
//	    return false; 
//	} 

    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_POWER || (event.getKeyCode() == KeyEvent.KEYCODE_VOLUME_DOWN) || (event.getKeyCode() == KeyEvent.KEYCODE_POWER)) {
            //Intent i = new Intent(this, NewActivity.class);
            //startActivity(i);
            return false;
        }
        if ((event.getKeyCode() == KeyEvent.KEYCODE_HOME)) {

            System.out.println("alokkkkkkkkkkkkkkkkk");
            return true;
        }
        return false;
    }

    /*public void unloack(){

          finish();

    }*/
    public void onDestroy() {
        // k1.reenableKeyguard();

        super.onDestroy();
    }


}