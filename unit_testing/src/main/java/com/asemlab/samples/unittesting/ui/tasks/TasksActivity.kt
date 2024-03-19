package com.asemlab.samples.unittesting.ui.tasks

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.asemlab.samples.unittesting.R
import com.asemlab.samples.unittesting.databinding.ActivityTasksBinding

class TasksActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTasksBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_tasks)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        with(binding.tasksRV) {
            layoutManager = LinearLayoutManager(this@TasksActivity)
            adapter = TasksAdapter(
                listOf(
                    Task(1, "Shopping", 3),
                    Task(2, "Washing", 3),
                    Task(3, "Cleaning", 1),
                    Task(4, "Studying", 2),
                    Task(5, "Workout", 1),
                )
            )
        }
    }
}