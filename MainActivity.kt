package com.example.assignment2

import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var batteryReceiver : BatteryReceiver
    val headReceiver = headsetReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textBattery = findViewById<TextView>(R.id.textView11)

        val serviceIntent = Intent(this, backgroundService::class.java)
        startService(serviceIntent)

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
        registerReceiver(batteryReceiver, connectivityFilter)
        registerReceiver(headReceiver, headsetFilter)
    }
}