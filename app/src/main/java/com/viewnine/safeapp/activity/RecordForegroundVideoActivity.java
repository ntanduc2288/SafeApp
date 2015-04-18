package com.viewnine.safeapp.activity;

import android.content.Intent;
import android.os.Bundle;

import com.viewnine.safeapp.service.MyService;

/**
 * Created by user on 4/19/15.
 */
public class RecordForegroundVideoActivity extends ParentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupViews();

        startService(new Intent(this, MyService.class));
    }

    private void setupViews() {
        addChidlView(R.layout.record_foreground_video_view);
        showHideHeader(false);
    }
}
