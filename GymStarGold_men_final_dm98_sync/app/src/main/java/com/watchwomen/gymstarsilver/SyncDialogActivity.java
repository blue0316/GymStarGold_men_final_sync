package com.watchwomen.gymstarsilver;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


public class SyncDialogActivity extends AppCompatActivity implements View.OnClickListener {

    public Activity c;
    public Dialog d;
    public Button sync, offline;
    TextView txt_status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sync_dialog);
        sync = (Button) findViewById(R.id.btn_sync);
        offline = (Button) findViewById(R.id.btn_offline);
        txt_status  = (TextView) findViewById(R.id.txt_status);

        sync.setOnClickListener(this);
        offline.setOnClickListener(this);

        String syncStatus = getIntent().getStringExtra("syncStatus");
        if(syncStatus.equals("true")){
            txt_status.setText("There are unsaved local data. Do you want to sync now or continue with offline mode?");
        }else{
            txt_status.setText("No unsaved local data available. Do you want to sync website data?");
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_offline:
                AppPreferences.getInstance().setOfflineStatus(true);

                Intent i = new Intent(SyncDialogActivity.this, NavigationMenuActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra( "mode", "offline" );
                startActivity(i);
                finish();
                break;
            case R.id.btn_sync:
                Intent in = new Intent(SyncDialogActivity.this, SyncActivity.class);
                in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                in.putExtra( "mode", "offline" );
                startActivity(in);
                finish();
                break;
            default:
                break;
        }

    }



}