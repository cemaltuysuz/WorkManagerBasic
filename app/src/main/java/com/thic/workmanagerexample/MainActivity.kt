package com.thic.workmanagerexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager

class MainActivity : AppCompatActivity() {

    private lateinit var btn:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn = findViewById(R.id.notifyButton)
        val mWorkManager = WorkManager.getInstance(applicationContext)
        val mRequest = OneTimeWorkRequest.Builder(NotifyWorker::class.java)
                .build()

        btn.setOnClickListener(View.OnClickListener {
            mWorkManager.enqueue(mRequest)
        })

        mWorkManager.getWorkInfoByIdLiveData(mRequest.id).observe(this, Observer {
            toastBuilder("State ${it.state}")
            toastBuilder("Output ${it.outputData}")
            toastBuilder("Progress ${it.progress}")
        })

    }

    private fun toastBuilder(msg:String){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
    }
}