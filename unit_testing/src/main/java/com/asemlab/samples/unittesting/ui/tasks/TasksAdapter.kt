package com.asemlab.samples.unittesting.ui.tasks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.asemlab.samples.unittesting.databinding.TaskItemBinding

class TasksAdapter(private val tasks: List<Task>) :
    RecyclerView.Adapter<TasksAdapter.TaskViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder.inflate(LayoutInflater.from(parent.context), parent)
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(tasks[position])
    }

    class TaskViewHolder private constructor(private val binding: TaskItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun inflate(inflater: LayoutInflater, parent: ViewGroup): TaskViewHolder {
                return TaskViewHolder(TaskItemBinding.inflate(inflater, parent, false))
            }
        }

        fun bind(item: Task) {
            binding.task = item
        }
    }

}