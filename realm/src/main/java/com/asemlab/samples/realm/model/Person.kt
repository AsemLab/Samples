package com.asemlab.samples.realm.model

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.ext.realmSetOf
import io.realm.kotlin.types.RealmInstant
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.RealmSet
import io.realm.kotlin.types.annotations.Ignore
import io.realm.kotlin.types.annotations.PersistedName
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

// TODO Define entity object
// Must be a child class of RealmObject, have empty constructor, and cannot be a data class
@PersistedName("persons") // Change class name in Realm
class Person() : RealmObject {
    @PrimaryKey // You can define only one primary key
    var _id = ObjectId() // Or RealmUUID.random()
    var name: String = ""

    @PersistedName("mobile")
    var phoneNumber: String? = ""
    var childrenList: RealmList<Child> = realmListOf() // Define property as List

    var childrenSet: RealmSet<Child> = realmSetOf() // Define property as Set

    //    var addresses: RealmDictionary<Address>? = realmDictionaryOf() // Define property as Map<String, Address>
    @Ignore // To ignore this property from Realm
    //    var child: Child? = Child() // RealmObject must be nullable, and cannot be a primary key
    var timeCreated: RealmInstant =
        RealmInstant.now() // Realm datetime property as '2024-02-06T12:02:25.371Z'

    var age = 0

    override fun toString(): String {
        return "$name, $phoneNumber"
    }
}