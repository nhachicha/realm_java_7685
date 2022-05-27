package io.realm.example.myapplication.di

import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import io.realm.Realm
import io.realm.example.myapplication.MainActivity
import io.realm.example.myapplication.model.Person
import io.realm.kotlin.where
import javax.inject.Inject
import javax.inject.Singleton


interface Storage {
    fun addPerson(name: String, age: Int)
    fun getPerson(name: String): Person?
}

class RealmStorage @Inject constructor(val realm: Realm) : Storage {

    override fun addPerson(name: String, age: Int) {
        realm.executeTransaction {
            it.insert(Person().apply { this.name = name; this.age = age })
        }
    }

    override fun getPerson(name: String): Person? {
        return realm.where<Person>().findFirst()
    }
}

@Module
abstract class StorageModule {
    @Binds
    abstract fun provideStorage(storage: RealmStorage): Storage
}

@Singleton
@Component(modules = [StorageModule::class])
interface AppComponent {

    fun inject(activity: MainActivity)

    @Component.Factory
    interface Factory {
        // With @BindsInstance, the Context passed in will be available in the graph
        fun create(@BindsInstance realm: Realm): AppComponent
    }
}