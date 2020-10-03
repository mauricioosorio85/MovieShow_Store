package com.example.movieshow_store.app

import android.app.Application
import com.example.movieshow_store.models.*
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmObject
import java.util.concurrent.atomic.AtomicInteger

class Config : Application(){

    override fun onCreate() {
        super.onCreate()

        setupConfig()
        val realm = Realm.getDefaultInstance()
        cliente = getIdByTable(realm, Cliente::class.java)
        cd = getIdByTable(realm, Cd::class.java)
        alquiler = getIdByTable(realm, Alquiler::class.java)
        realm.close()

    }

    private fun <T : RealmObject> getIdByTable(realm: Realm, anyclass: Class<T>): AtomicInteger {
        val results = realm.where(anyclass).findAll()
        return if (results.size > 0) AtomicInteger(results.max("id")!!.toInt()) else AtomicInteger()
    }

    private fun setupConfig() {
        Realm.init(applicationContext)
        val realmConfiguration = RealmConfiguration.Builder()
            .deleteRealmIfMigrationNeeded()
            .build()
        Realm.setDefaultConfiguration(realmConfiguration)
    }

    companion object {
       var cliente = AtomicInteger()
       var cd = AtomicInteger()
       var alquiler = AtomicInteger()
    }
}