package com.viewnine.safeapp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by user on 4/18/15.
 */
public class SetupActivity extends Activity implements View.OnClickListener{


    private TextView txtPrimaryEmail;
    private TextView txtSecondaryEmail;
    private Button btnNext;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setupViews();
    }

    private void setupViews() {
        setContentView(R.layout.setupscreen_view);
        txtPrimaryEmail = (TextView) findViewById(R.id.primary_email);
        txtSecondaryEmail = (TextView) findViewById(R.id.secondary_email);
        btnNext = (Button) findViewById(R.id.next);
    }

    @Override
    public void onClick(View v) {

    }
}
