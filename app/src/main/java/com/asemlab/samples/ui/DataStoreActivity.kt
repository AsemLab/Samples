package com.asemlab.samples.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.asemlab.samples.R
import com.asemlab.samples.databinding.ActivityDatastoreBinding
import com.asemlab.samples.utils.DataStoreUtils
import kotlinx.coroutines.launch

class DataStoreActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDatastoreBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_datastore)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        with(binding) {

            lifecycleScope.launch {
                usernameTV.text = DataStoreUtils.getUsername(this@DataStoreActivity)
                genderTV.text = DataStoreUtils.getGender(this@DataStoreActivity).toString()
                ageTV.text = DataStoreUtils.getAge(this@DataStoreActivity).toString()
            }


            addButton.setOnClickListener {
                lifecycleScope.launch {
                    DataStoreUtils.setUsername(this@DataStoreActivity, "Asem97")
                    DataStoreUtils.setAge(this@DataStoreActivity, 28)
                    DataStoreUtils.setGender(this@DataStoreActivity, true)

                    recreate()
                }
            }
        }

    }
}