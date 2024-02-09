package com.asemlab.samples.realm.model

import io.realm.kotlin.types.RealmObject

class Child() : RealmObject {
    var name: String = ""

    override fun toString(): String {
        return name
    }
}