package com.asemlab.samples.base.epoxy

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.asemlab.samples.base.R
import com.asemlab.samples.base.databinding.ActivityEpoxyBinding

class EpoxyActivity : AppCompatActivity() {

    lateinit var binding: ActivityEpoxyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_epoxy)

        val controller = ItemsController().apply {
            setData((1..10).map { it.toString() }, true)
        }

        binding.itemsRV.apply {
            layoutManager = LinearLayoutManager(this@EpoxyActivity)
            setController(controller)
        }
    }
}