package com.viewnine.safeapp.manager;

import android.content.SharedPreferences;

import com.viewnine.safeapp.application.SafeAppApplication;

/**
 * Created by user on 4/18/15.
 */
public class SharePreferenceManager {
    private static final String PREFS_NAME = "SafeApp_Frefs";
    private static final String IS_FIRST_TIME_RUNNING = "IS_FIRST_TIME_RUNNING";

    private static SharePreferenceManager instance;
    private static SharedPreferences preferences;

    public static SharePreferenceManager getInstance() {
        if (instance == null) {
            synchronized (SharePreferenceManager.class) {
                if (instance == null) {
                    instance = new SharePreferenceManager();
                    preferences = SafeAppApplication.getInstance().getSharedPreferences(PREFS_NAME, 0 );
                }
            }
        }

        return instance;
    }

    public void setFirstTimeRunningApp(boolean isFirstTimeRunningApp) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(IS_FIRST_TIME_RUNNING, isFirstTimeRunningApp);
        editor.commit();
    }

    public boolean isFirstTimeRunningApp(){
        return preferences.getBoolean(IS_FIRST_TIME_RUNNING, false);
    }
}
