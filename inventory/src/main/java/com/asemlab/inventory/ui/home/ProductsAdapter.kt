package com.asemlab.inventory.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.asemlab.inventory.databinding.ProductItemBinding
import com.asemlab.inventory.ui.models.ItemUI

class ProductsAdapter(private val list: MutableList<ItemUI>) :
    RecyclerView.Adapter<ProductsAdapter.VH>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): VH {
        return VH.from(parent)
    }

    override fun onBindViewHolder(
        holder: VH,
        position: Int
    ) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size


    fun updateList(newList: List<ItemUI>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    class VH private constructor(private val binding: ProductItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(product: ItemUI) {
            binding.product = product

            binding.root.setOnClickListener {

            }
        }


        companion object {

            fun from(parent: ViewGroup): VH {
                val inflater = LayoutInflater.from(parent.context)

                val binding = ProductItemBinding.inflate(inflater, parent, false)

                return VH(binding)
            }
        }
    }

}