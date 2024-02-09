package com.asemlab.samples.realm.ui.fragments

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asemlab.samples.realm.database.PersonRepository
import com.asemlab.samples.realm.model.Child
import com.asemlab.samples.realm.model.Person
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val personRepository: PersonRepository) :
    ViewModel() {

    val persons = MutableStateFlow<List<Person>>(emptyList())
    val personChildren = MutableLiveData<List<Child>>(emptyList())
    val personName = MutableLiveData("")
    val personMobile = MutableLiveData("")
    val personId = MutableLiveData("")
    var clickedPerson = ""

    init {
        getPersons()
    }

    fun getPersons() {
        viewModelScope.launch {
            personRepository.getAllPersons().collect {
                persons.emit(it)
            }
        }
    }

    fun addPerson(person: Person) {
        viewModelScope.launch {
            personRepository.insertPerson(person)
        }
    }

    fun deletePerson(person: Person) {
        viewModelScope.launch {
            personRepository.deletePerson(person._id)
        }
    }

    fun updatePerson(person: Person) {
        viewModelScope.launch {
            personRepository.updatePerson(person)
        }
    }

    fun getPersonChildren(person: Person) {
        viewModelScope.launch {
            clickedPerson = person.name
            personChildren.value = personRepository.getChildrenList(person._id)
        }
    }

    fun getPersonsByName(name: String) {
        viewModelScope.launch {
            personRepository.getPersonsByName(name).collect {
                persons.emit(it)
            }
        }
    }

}