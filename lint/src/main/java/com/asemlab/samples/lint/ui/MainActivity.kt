package com.asemlab.samples.lint.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.asemlab.samples.lint.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // Some comment to test Ktlint

        val x = 5
    }
}
