package com.example.services

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.Button
import java.util.*

private const val LOG_TAG = "myActivity"

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    var bound = false
    var sConn: ServiceConnection? = null
    var intentService: Intent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val startBtn = findViewById<Button>(R.id.start_button)
        val stopBtn = findViewById<Button>(R.id.stop_button)
        intentService = Intent(this, MyService::class.java)
        sConn = object : ServiceConnection {
            override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
                Log.d(LOG_TAG, "MainActivity onServiceConnected")
                bound = true
            }
            override fun onServiceDisconnected(p0: ComponentName?) {
                Log.d(LOG_TAG, "MainActivity onServiceDisconnected")
                bound=false
            }
        }

        startBtn.setOnClickListener { onClickStart() }
        stopBtn.setOnClickListener { onClickStop() }

    }

    private fun onClickStart(){ startService(intentService)}
    private fun onClickStop(){
        Log.d(LOG_TAG, "onClickStop")
        stopService(intentService)
    }
    fun onClickBind(){ bindService(intentService, sConn!!, BIND_AUTO_CREATE)}
    private fun onClickUnBind(){
        if (!bound) return
        unbindService(sConn!!)
        bound = false
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(LOG_TAG, "onDestroy")
        onClickUnBind()
    }
}