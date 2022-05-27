package io.realm.example.myapplication.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Person : RealmObject() {
    @PrimaryKey
    var name: String = ""
    var age = 0
}