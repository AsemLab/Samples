package com.asemlab.samples.feature.hotel.ui.fragments.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.asemlab.samples.feature.hotel.databinding.HotelItemBinding
import com.asemlab.samples.feature.hotel.model.Hotel

class HotelsAdapter(private val items: MutableList<Hotel>, private val onClick: (Hotel) -> Unit) :
    RecyclerView.Adapter<HotelsAdapter.HotelsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotelsViewHolder {
        return HotelsViewHolder.from(parent)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: HotelsViewHolder, position: Int) {
        holder.bind(items[position], onClick)
    }

    fun updateData(newList: List<Hotel>) {
        items.clear()
        items.addAll(newList)
        notifyDataSetChanged()
    }


    class HotelsViewHolder(private val binding: HotelItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(hotel: Hotel, onClick: (Hotel) -> Unit) {
            binding.hotel = hotel
            binding.root.setOnClickListener {
                onClick(hotel)
            }
        }

        companion object {
            fun from(parent: ViewGroup): HotelsViewHolder {
                val binding =
                    HotelItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return HotelsViewHolder(binding)
            }
        }

    }

}