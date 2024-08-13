package com.asemlab.samples.feature.hotel.ui.fragments.hotel

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.asemlab.samples.feature.hotel.databinding.RateItemBinding
import com.asemlab.samples.feature.hotel.model.Rate

class RatesAdapter(private val items: MutableList<Rate>) :
    RecyclerView.Adapter<RatesAdapter.RatesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RatesViewHolder {
        return RatesViewHolder.from(parent)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RatesViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun updateData(newList: List<Rate>) {
        items.clear()
        items.addAll(newList)
        notifyDataSetChanged()
    }

    class RatesViewHolder(private val binding: RateItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(rate: Rate) {
            binding.rate = rate
        }

        companion object {
            fun from(parent: ViewGroup): RatesViewHolder {
                val binding =
                    RateItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return RatesViewHolder(binding)
            }
        }
    }

}