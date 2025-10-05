package com.asemlab.samples.base.epoxy

import com.airbnb.epoxy.Typed2EpoxyController
import com.asemlab.samples.base.epoxy.ItemModel_

class ItemsController : Typed2EpoxyController<List<String>, Boolean>() {

    override fun buildModels(data1: List<String>?, data2: Boolean?) {
        data1?.forEach { it ->
            ItemModel_().id(it).title(it).addTo(this)
        }
    }
}