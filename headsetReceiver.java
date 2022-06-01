package com.example.assignment2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class headsetReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if(Intent.ACTION_HEADSET_PLUG.equals(intent.getAction()))
        {
            boolean noHeadset = intent.getBooleanExtra(Intent.ACTION_HEADSET_PLUG, true);
            if(noHeadset)
            {
                Toast.makeText(context, "Headphones Disconnected", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(context, "Headphones Connected", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
