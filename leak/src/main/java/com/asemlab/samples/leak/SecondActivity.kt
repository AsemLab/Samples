package com.asemlab.samples.leak

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        MainActivity.context = this
        Handler().postDelayed({
            finish()
        }, 2000)

    }


}