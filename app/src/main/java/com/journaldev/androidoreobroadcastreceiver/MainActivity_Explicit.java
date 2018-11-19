package com.journaldev.androidoreobroadcastreceiver;

import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.sql.Connection;

public class MainActivity_Explicit extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity_Explicit.class.getSimpleName();
    Button btnExplicitBroadcast,btnbattery;

    MyReceiver myReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnExplicitBroadcast = findViewById(R.id.btnExplicitBroadcast);
        btnExplicitBroadcast.setOnClickListener(this);

        myReceiver= new MyReceiver();

        //implicit
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");//ConnectivityManager.CONNECTIVITY_ACTION
        registerReceiver(myReceiver, filter);

        //implicit
        IntentFilter filter1 = new IntentFilter(Intent.ACTION_BATTERY_LOW);
        registerReceiver(myReceiver, filter1);

        //implicit
        IntentFilter filter2 = new IntentFilter(Intent.ACTION_PACKAGE_ADDED);
        filter2.addDataScheme("package");
        registerReceiver(myReceiver, filter2);

        //implicit
        IntentFilter filter3 = new IntentFilter(Intent.ACTION_PACKAGE_REMOVED);
        filter3.addDataScheme("package");
        registerReceiver(myReceiver, filter3);

    }

    public void broadcastIntent() {

        Log.d(TAG,"Simple Name      = "+MyReceiver.class.getSimpleName());
        Log.d(TAG,"Name             = "+MyReceiver.class.getName());
        Log.d(TAG,"Canonical Name   = "+MyReceiver.class.getCanonicalName());

        Intent intent = new Intent();
        intent.setAction("com.journaldev.AN_INTENT");
        intent.setComponent(new ComponentName(getPackageName(),MyReceiver.class.getName()));
        getApplicationContext().sendBroadcast(intent);
    }

    @Override
    protected void onStop() {
        super.onStop();
        //unregisterReceiver(myReceiver);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnExplicitBroadcast:
                broadcastIntent();
                break;
        }
    }
}