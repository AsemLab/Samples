package com.asemlab.samples.activity_recognition.ui.stats

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.asemlab.samples.activity_recognition.databinding.EntryItemBinding
import com.asemlab.samples.activity_recognition.model.ActivityEntry

class EntriesAdapter(
    private val entries: MutableList<ActivityEntry>,
    private val onItemClick: (ActivityEntry) -> Unit
) :
    RecyclerView.Adapter<EntriesAdapter.EntriesVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntriesVH {
        return EntriesVH.createVH(parent)
    }

    override fun getItemCount(): Int = entries.size

    override fun onBindViewHolder(holder: EntriesVH, position: Int) {
        holder.bind(entries[position], onItemClick)
    }

    fun setItems(items: List<ActivityEntry>) {
        with(entries) {
            clear()
            addAll(items)
            notifyDataSetChanged()
        }
    }

    class EntriesVH private constructor(private val binding: EntryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(entry: ActivityEntry, onItemClick: (ActivityEntry) -> Unit) {

            with(binding) {
                this.entry = entry
                root.setOnClickListener {
                    onItemClick(entry)
                }
            }
        }

        companion object {

            fun createVH(parent: ViewGroup): EntriesVH {
                val binding =
                    EntryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

                return EntriesVH(binding)
            }
        }
    }

}