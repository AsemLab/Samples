package com.asemlab.samples.base.epoxy

import android.view.View
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.asemlab.samples.base.R
import com.asemlab.samples.base.databinding.ItemItemBinding

@EpoxyModelClass
abstract class ItemModel: EpoxyModelWithHolder<ItemModel.ItemHolder>(){

    @EpoxyAttribute
    var title: String? = null

    override fun bind(holder: ItemHolder) {
        super.bind(holder)
        holder.binding.title.text = title
    }

    override fun getDefaultLayout(): Int {
        return R.layout.item_item
    }

    class ItemHolder: EpoxyHolder() {

        lateinit var binding: ItemItemBinding

        override fun bindView(itemView: View) {
            binding = ItemItemBinding.bind(itemView)
        }

    }

}