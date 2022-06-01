package com.example.assignment2

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.TextView

class BatteryReceiver(val textView: TextView) : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val batteryLevel = intent?.getIntExtra("level", 0)

        textView.text = if(batteryLevel?:0 >= 0) batteryLevel.toString() else ""
    }
}