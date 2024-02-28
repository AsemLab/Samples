package com.asemlab.samples.koin

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.asemlab.samples.koin.databinding.HistoryItemBinding
import com.asemlab.samples.koin.model.Country

class HistoryAdapter(private val items: MutableList<Country>, private val onClick: (Country) -> Unit) :
    RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        return HistoryViewHolder.from(parent)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(items[position], onClick)
    }

    fun updateData(newList: List<Country>) {
        items.clear()
        items.addAll(newList)
        notifyDataSetChanged()
    }


    class HistoryViewHolder(private val binding: HistoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(country: Country, onClick: (Country) -> Unit) {
            binding.root.setOnClickListener {
                onClick(country)
            }

            binding.itemTV.text = "$country"
        }

        companion object {
            fun from(parent: ViewGroup): HistoryViewHolder {
                val binding =
                    HistoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return HistoryViewHolder(binding)
            }
        }

    }

}