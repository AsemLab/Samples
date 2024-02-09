package com.asemlab.samples.realm.database

import com.asemlab.samples.realm.model.Child
import com.asemlab.samples.realm.model.Person
import io.realm.kotlin.query.RealmResults
import kotlinx.coroutines.flow.Flow
import org.mongodb.kbson.ObjectId

interface PersonRepository {
    fun getAllPersons(): Flow<List<Person>>
    suspend fun insertPerson(person: Person)
    suspend fun updatePerson(person: Person)
    suspend fun deletePerson(id: ObjectId)
    fun getChildrenList(id: ObjectId): List<Child>
    fun getAllPersonsHaveMore(childrenCount: Int): List<Person>
    fun getPersonsByName(name: String): Flow<List<Person>>
    fun getPersonsByNameDesc(name: String): Flow<RealmResults<Person>>
    suspend fun increasePersonAgeBy(amount: Int)
    suspend fun updateChildOf(id: ObjectId, index: Int, newName: String)
}