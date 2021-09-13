package com.example.services

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.util.Log

private const val LOG_TAG = "myLog"


class MyService : Service() {
    val handler = Handler(Looper.getMainLooper())

    override fun onCreate() {
        super.onCreate()
        Log.d(LOG_TAG, "onCreateCommand")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(LOG_TAG, "onStartCommand")
        if (intent?.getBooleanExtra(NAME, false) == true) {
            stopSelf()
            return super.onStartCommand(intent, flags, startId)
        }
        handler.postDelayed(object : Runnable {
            override fun run() {
                someTask()
                handler.postDelayed(this, 5000)
            }
        }, 0)
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        Log.d(LOG_TAG, "onDestroyCommand")
        handler.removeCallbacksAndMessages(null)
        super.onDestroy()
    }

    override fun onBind(intent: Intent): IBinder? {
        Log.d(LOG_TAG, "onBindCommand")
        return null
    }

    private fun someTask() {
        Log.d(LOG_TAG, "num = ${(0..1000).random()}")
    }
}