package com.asemlab.samples.chuncker.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.asemlab.samples.chuncker.databinding.ItemCountryBinding
import com.asemlab.samples.chuncker.models.CountryResponseItem

class CountryAdapter(private val onClick: (CountryResponseItem) -> Unit) :
    RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    private val countries = mutableListOf<CountryResponseItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        return CountryViewHolder.inflate(LayoutInflater.from(parent.context), parent)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(countries[position], onClick)
    }

    override fun getItemCount(): Int {
        return countries.size
    }

    fun addItems(newItems: List<CountryResponseItem>) {
        countries.addAll(newItems)
        notifyDataSetChanged()
    }

    class CountryViewHolder private constructor(private val binding: ItemCountryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun inflate(inflater: LayoutInflater, parent: ViewGroup): CountryViewHolder {
                return CountryViewHolder(ItemCountryBinding.inflate(inflater, parent, false))
            }
        }

        fun bind(item: CountryResponseItem, onClick: (CountryResponseItem) -> Unit) {
            binding.country = item
            binding.root.setOnClickListener {
                onClick(item)
            }
        }
    }

}