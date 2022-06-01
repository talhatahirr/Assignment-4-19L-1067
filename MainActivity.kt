package com.example.assignment2

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi

class MainActivity : AppCompatActivity() {

    lateinit var batteryReceiver : BatteryReceiver
    val connectivityReceiver = ConnectivityReceiver()
    val headReceiver = headsetReceiver()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textBattery = findViewById<TextView>(R.id.textView11)

        val serviceIntent = Intent(this, backgroundService::class.java)
        startService(serviceIntent)

        val foregroundServiceIntent = Intent(this, foregroundService::class.java)
        startForegroundService(foregroundServiceIntent)

        batteryReceiver = BatteryReceiver(textBattery)
        registerReceiver(batteryReceiver, IntentFilter(Intent.ACTION_BATTERY_CHANGED))

        val start = findViewById<Button>(R.id.button1)
        start.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        val connectivityFilter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        val headsetFilter = IntentFilter(Intent.ACTION_HEADSET_PLUG)
        registerReceiver(connectivityReceiver, connectivityFilter)
        registerReceiver(headReceiver, headsetFilter)
    }
}