package com.asemlab.samples.realm.database

import android.util.Log
import com.asemlab.samples.realm.model.Child
import com.asemlab.samples.realm.model.Person
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import io.realm.kotlin.ext.toRealmList
import io.realm.kotlin.ext.toRealmSet
import io.realm.kotlin.query.Sort
import kotlinx.coroutines.flow.map
import org.mongodb.kbson.ObjectId
import kotlin.random.Random

class PersonRepoImp(private val realm: Realm) : PersonRepository {

    // TODO Read data from Realm and transform it into List<Person>
    override fun getAllPersons() = realm.query<Person>().asFlow().map { it.list }

    // TODO Insert into Realm
    override suspend fun insertPerson(person: Person) {
        realm.writeBlocking {
            // TODO Add random number of children
            person.apply {
                val children = buildList {
                    repeat(Random.nextInt(0, 5)) {
                        add(Child().apply { name = "Child #$it" })
                    }
                }
                childrenList = children.toRealmList()
                childrenSet = children.toRealmSet()
            }
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

    // TODO Read a RealmList
    override fun getChildrenList(id: ObjectId): List<Child> {
        val p = realm.query<Person>(query = "_id == $0", id).find().first()
        return p.childrenList.toList()
    }

    // TODO Read a RealmSet and apply filter
    override fun getAllPersonsHaveMore(childrenCount: Int): List<Person> {
        val personsQuery =
            realm.query<Person>(query = "childrenSet.@size > $0", childrenCount).find()
        return personsQuery.toList()
    }

    // TODO Filter by property(name)
    override fun getPersonsByName(name: String) =
        realm.query<Person>(query = "name CONTAINS $0", name).find().asFlow().map { it.list }

    // TODO Filter by property(name) and sort descending
    override fun getPersonsByNameDesc(name: String) =
        realm.query<Person>(query = "name CONTAINS $0", name).sort("name", Sort.DESCENDING).find()
            .asFlow().map { it.list }

}