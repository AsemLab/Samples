package com.asemlab.samples.graphql.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.asemlab.samples.graphql.databinding.CountryItemBinding
import com.asemlab.samples.graphql.model.SimpleCountry

class CountriesAdapter(
    private val items: MutableList<SimpleCountry>,
    private val onClick: (SimpleCountry) -> Unit
) : RecyclerView.Adapter<CountriesAdapter.CountriesVH>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountriesVH {
        return CountriesVH.from(parent)
    }

    override fun onBindViewHolder(holder: CountriesVH, position: Int) {
        holder.bind(items[position], onClick)
    }

    override fun getItemCount() = items.size

    fun updateData(newData: List<SimpleCountry>) {
        items.clear()
        items.addAll(newData)
        notifyDataSetChanged()
    }

    class CountriesVH private constructor(private val binding: CountryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(country: SimpleCountry, onClick: (SimpleCountry) -> Unit) {
            with(binding) {
                this.country = country
                root.setOnClickListener {
                    onClick(country)
                }
            }
        }

        companion object {
            fun from(parent: ViewGroup): CountriesVH {
                val binding =
                    CountryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return CountriesVH(binding)
            }
        }

    }

}