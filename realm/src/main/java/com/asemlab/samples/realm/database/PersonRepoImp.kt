package com.asemlab.samples.realm.database

import android.util.Log
import com.asemlab.samples.realm.model.Child
import com.asemlab.samples.realm.model.Person
import io.realm.kotlin.Realm
import io.realm.kotlin.UpdatePolicy
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
                val children = getRandomChildren()
                childrenList = children.toRealmList()
                childrenSet = children.toRealmSet()
                age = Random.nextInt(18, 64)
            }
            copyToRealm(person, UpdatePolicy.ALL) // TODO Enable update when same id is exists
        }
    }

    private fun getRandomChildren() = buildList {
        repeat(Random.nextInt(0, 5)) {
            add(Child().apply { name = "Child #$it" })
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
    override suspend fun getChildrenList(id: ObjectId): List<Child> {
        val p = realm.query<Person>(query = "_id == $0", id).find().first()
        return p.childrenList.toList()
    }

    // TODO Read a RealmSet and apply filter
    override suspend fun getAllPersonsHaveMore(childrenCount: Int): List<Person> {
        val personsQuery =
            realm.query<Person>(query = "childrenSet.@size > $0", childrenCount).find()
        return personsQuery.toList()
    }

    // TODO Filter by property(name)
    override suspend fun getPersonsByName(name: String) =
        realm.query<Person>(query = "name CONTAINS $0", name).find().asFlow().map { it.list }

    // TODO Filter by property(name) and sort descending
    override suspend fun getPersonsByNameDesc(name: String) =
        realm.query<Person>(query = "name CONTAINS[c] $0", name).sort("name", Sort.DESCENDING)
            .find() // TODO Adding [c] after (contains) for case insensitive
            .asFlow().map { it.list }

    // TODO Update multiple objects in Realm
    override suspend fun increasePersonAgeBy(amount: Int) {
        realm.writeBlocking {
            val persons = query<Person>().find()
            for (person in persons) {
                person.age += amount
            }
        }
    }

    // TODO Update RealmList and add to it, SAME for RealmSet & RealmDictionary
    override suspend fun updateChildOf(id: ObjectId, index: Int, newName: String) {
        realm.writeBlocking {
            val person = query<Person>("_id == $0", id).find().first()
//            person.childrenList.addAll(getRandomChildren().toList()) // To Add to RealmList
            person.childrenList[index].apply {
                name = newName
            }

        }
    }

    // TODO Delete all objects of a type
    override suspend fun deleteAllPersons() {
        realm.writeBlocking {
            delete(query<Person>().find())
        }
    }

    // TODO Delete multiple objects
    override suspend fun deleteTwoOldest() {
        realm.writeBlocking {
            val query = query<Person>("age > 0 sort(age DESC)").limit(2).find()
            delete(query)
        }
    }

    // TODO Clear the database
    override suspend fun clearDatabase() {
        realm.writeBlocking {
            deleteAll()
        }
    }

    // TODO Delete from RealmList, SAME for RealmSet & RealmDictionary
    override suspend fun removeAllChildren(id: ObjectId, index: Int) {
        realm.writeBlocking {
            val person = query<Person>("_id == $0", id).find().first()
//            person.childrenList.removeAt(index) // To remove at index
            person.childrenList.clear()
        }
    }
}