package com.asemlab.samples.unittesting.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.asemlab.samples.unittesting.R
import com.asemlab.samples.unittesting.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        with(binding) {

            loginButton.setOnClickListener {
                val username = usernameET.text.toString()
                val password = passwordET.text.toString()

                if (username == correctUsername && password == correctPassword) {
                    val intent = Intent(this@LoginActivity, UserActivity::class.java).putExtra(
                        "USERNAME",
                        correctUsername
                    )
                    startActivity(intent)
                }
            }

        }


    }

    companion object {
        val correctUsername = "Asem"
        val correctPassword = "0000"
    }
}