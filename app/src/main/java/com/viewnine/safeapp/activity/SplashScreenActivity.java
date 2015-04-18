package com.viewnine.safeapp.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

/**
 * Created by user on 4/18/15.
 */
public class SplashScreenActivity extends Activity {
    Context mContext;
    static int key_exit = 1;
    private String TAG = SplashScreenActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = SplashScreenActivity.this;
        setContentView(R.layout.splashscreen_view);

        handler.sendEmptyMessageDelayed(key_exit, 1000);
    }

    Handler handler = new Handler(new Handler.Callback() {

        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == key_exit) {
                handleGotoNextScreen();
            }
            return false;
        }


    });

    private void handleGotoNextScreen() {

       /* if(SharePreferenceManager.getInstance().isFirstTimeRunningApp()){
            SharePreferenceManager.getInstance().setFirstTimeRunningApp(true);
            Intent intent = new Intent(this, SetupActivity.class);
            startActivity(intent);
            finish();
        }else {

        }*/

        Intent intent = new Intent(this, SetupActivity.class);
        startActivity(intent);
        finish();

    }


}
