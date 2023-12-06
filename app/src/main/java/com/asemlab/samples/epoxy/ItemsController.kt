package com.asemlab.samples.epoxy

import com.airbnb.epoxy.Typed2EpoxyController

class ItemsController : Typed2EpoxyController<List<String>, Boolean>() {

    override fun buildModels(data1: List<String>?, data2: Boolean?) {
        data1?.forEach { it ->
            ItemModel_().id(it).title(it).addTo(this)
        }
    }
}