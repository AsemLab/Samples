package com.asemlab.samples.base.passdata.a2a

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.asemlab.samples.base.R
import com.asemlab.samples.base.databinding.ActivityFirstBinding
import com.asemlab.samples.base.passdata.a2f_f2a.AlertFragment

class FirstActivity : AppCompatActivity() {

    val viewModel by viewModels<FirstViewModel>()
    private lateinit var binding: ActivityFirstBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_first)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        with(binding) {
            submitButton.setOnClickListener {
                val text = textED.text.toString()

                val intent = Intent(this@FirstActivity, SecondActivity::class.java).apply {
                    putExtra("text", text)
                }

                startActivity(intent)
            }

            toFragButton.setOnClickListener {

                // Send data with constructor
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_section, AlertFragment("New title!")).commit()

                // Send data with setArguments
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_section, AlertFragment("New title!").apply {
                        arguments = bundleOf("title" to "Hi There!")
                    }).commit()

                // Send data with bundle
                supportFragmentManager.beginTransaction()
                    .replace(
                        R.id.fragment_section,
                        AlertFragment::class.java,
                        bundleOf("title" to "Hi There!")
                    ).commit()

                supportFragmentManager.beginTransaction()
                    .replace(
                        R.id.fragment_section,
                        AlertFragment::class.java,
                        bundleOf("title" to "Hi There!"),
                        "frag"
                    ).commit()

            }

            updateFragButton.setOnClickListener {

                // Update data in Fragment, required a tag
                supportFragmentManager.findFragmentByTag("frag")?.let {
                    (it as AlertFragment).updateTitle("Updated Title!")
                }
            }

        }

        viewModel.title.observe(this) {
            binding.titleTV.text = it
        }

    }


}