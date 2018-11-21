package com.journaldev.androidoreobroadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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

        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver, new IntentFilter("speedExceeded"));

        IntentFilter filter = new IntentFilter(MyReceiver.class.getName());
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(myReceiver, filter);

        IntentFilter filter1 = new IntentFilter(Intent.ACTION_BATTERY_LOW);
        registerReceiver(myReceiver, filter1);


        IntentFilter filter2 = new IntentFilter(Intent.ACTION_PACKAGE_ADDED);
        filter2.addDataScheme("package");
        registerReceiver(myReceiver, filter2);

        IntentFilter filter3 = new IntentFilter(Intent.ACTION_PACKAGE_REMOVED);
        filter3.addDataScheme("package");
        registerReceiver(myReceiver, filter3);
    }

    public void broadcastIntent() {
        Intent intent = new Intent();
        intent.setAction("com.journaldev.AN_INTENT");
        intent.setComponent(new ComponentName(getPackageName(),"com.journaldev.androidoreobroadcastreceiver.MyReceiver"));
        getApplicationContext().sendBroadcast(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnExplicitBroadcast:
                broadcastIntent();
                break;
        }
    }


    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            String  callbackget =intent.getStringExtra("caller");
            String  callbackget1 =intent.getStringExtra("battery");
            Log.e(TAG,"connectivity  "+callbackget);
            Log.e(TAG,"battery_low"+callbackget1);
          //  Toast.makeText(context, ""+callbackget, Toast.LENGTH_SHORT).show();
            Toast.makeText(context, ""+callbackget1, Toast.LENGTH_SHORT).show();

            //  ... react to local broadcast message
        }
    };

}