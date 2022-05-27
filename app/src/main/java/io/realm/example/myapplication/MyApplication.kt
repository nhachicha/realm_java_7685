package io.realm.example.myapplication

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.example.myapplication.di.AppComponent
import io.realm.example.myapplication.di.DaggerAppComponent.factory


open class MyApplication : Application() {

    // Instance of the AppComponent that will be used by all the Activities in the project
    val appComponent: AppComponent by lazy {
        initializeComponent()
    }

    open fun initializeComponent(): AppComponent {
        Realm.init(applicationContext)

        val configuration = RealmConfiguration.Builder()
            .allowQueriesOnUiThread(true)
            .allowWritesOnUiThread(true)
            .build()
        val realm = Realm.getInstance(configuration)
        return factory().create(realm)
    }
}