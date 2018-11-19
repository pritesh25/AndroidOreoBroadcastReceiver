package com.journaldev.androidoreobroadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {


    private static final String TAG = MyReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {

        String action       = intent.getAction();
        String dataString   = intent.getDataString();
        String data         = String.valueOf(intent.getData());
        String packageName  = intent.getPackage();
        String scheme       = intent.getScheme();
        String type         = intent.getType();

        Log.d(TAG,"action       = "+action);
        Log.d(TAG,"dataString   = "+dataString);
        Log.d(TAG,"data         = "+data);
        Log.d(TAG,"packageName  = "+packageName);
        Log.d(TAG,"scheme       = "+scheme);
        Log.d(TAG,"type         = "+type);

        if (action.equals("com.journaldev.AN_INTENT")) {
            Toast.makeText(context, "Button Intent Trigger", Toast.LENGTH_SHORT).show();
        }

        if (("android.net.conn.CONNECTIVITY_CHANGE").equals(action)) {
            Toast.makeText(context, "Connectivity Change", Toast.LENGTH_SHORT).show();
        }

        if ((Intent.ACTION_BATTERY_LOW).equals(action)) {
            Toast.makeText(context, "Battery Low", Toast.LENGTH_SHORT).show();
        }

        if ((Intent.ACTION_PACKAGE_ADDED).equals(action)) {
            Toast.makeText(context, "package install", Toast.LENGTH_SHORT).show();
        }

        if ((Intent.ACTION_PACKAGE_REMOVED).equals(action)) {
            Toast.makeText(context, "package remove", Toast.LENGTH_SHORT).show();
        }
    }
}
