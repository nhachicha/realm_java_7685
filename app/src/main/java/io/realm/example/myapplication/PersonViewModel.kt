package io.realm.example.myapplication

import io.realm.example.myapplication.di.Storage
import io.realm.example.myapplication.model.Person
import javax.inject.Inject

class PersonViewModel @Inject constructor(private val storage: Storage) {

    fun add(name: String, age: Int) = storage.addPerson(name, age)

    fun find(name: String) : Person? = storage.getPerson(name)

}
