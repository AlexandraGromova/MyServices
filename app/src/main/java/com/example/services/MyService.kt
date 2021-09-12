package com.example.services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import java.util.*

private const val LOG_TAG = "myLog"

class MyService : Service() {

    override fun onCreate() {
        super.onCreate()
        Log.d(LOG_TAG, "onCreateCommand")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(LOG_TAG, "onStartCommand")
        someTask()
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        Log.d(LOG_TAG, "onDestroyCommand")
        super.onDestroy()
    }

    override fun onBind(p0: Intent?): IBinder? {
        Log.d(LOG_TAG, "onBindCommand")
        return null
    }

    private fun someTask(){
        Log.d(LOG_TAG, "startSomeTask")
        Thread.sleep(10000)
        Log.d(LOG_TAG, "finishSomeTask")
    }
}