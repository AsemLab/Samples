package com.asemlab.samples.realm.database

import android.util.Log
import com.asemlab.samples.realm.model.Person
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import kotlinx.coroutines.flow.map
import org.mongodb.kbson.ObjectId

class PersonRepoImp(private val realm: Realm) : PersonRepository {

    // TODO Read data from Realm and transform it into List<Person>
    override fun getAllPersons() = realm.query<Person>().asFlow().map { it.list }

    // TODO Insert into Realm
    override suspend fun insertPerson(person: Person) {
        realm.writeBlocking {
            copyToRealm(person)
        }
    }

    // TODO Update into Realm
    override suspend fun updatePerson(person: Person) {
        realm.writeBlocking {
            val queriedPerson = query<Person>(query = "_id == $0", person._id).first().find()
            queriedPerson?.name = person.name
            queriedPerson?.phoneNumber = person.phoneNumber
        }
    }

    // TODO Delete from Realm
    override suspend fun deletePerson(id: ObjectId) {
        realm.writeBlocking {
            val queriedPerson = query<Person>(query = "_id == $0", id).first().find()
            try {
                queriedPerson?.let { delete(it) }
            } catch (e: Exception) {
                Log.d("PersonRepoImp", "${e.message}")
            }

        }
    }
}