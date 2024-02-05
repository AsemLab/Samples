package com.asemlab.samples.realm.model

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

// TODO Define entity object
class Person : RealmObject {
    @PrimaryKey
    var _id = ObjectId()
    var name: String = ""
    var phoneNumber: String = ""
}