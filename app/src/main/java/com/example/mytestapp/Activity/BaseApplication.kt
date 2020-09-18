package com.example.mytestapp.Activity

import android.app.Application
import androidx.room.Room
import com.example.mytestapp.Activity.Model.Room.BigSummaryDatabase
import com.example.mytestapp.Activity.di.appModule
import com.example.mytestapp.Activity.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class BaseApplication :Application(){
    companion object {
        private lateinit var database: BigSummaryDatabase
        fun getDBInstance():BigSummaryDatabase{
            return database
        }
    }

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(this
            ,BigSummaryDatabase::class.java
            ,"creature_database").build()

        startKoin {
            androidLogger()
            androidContext(this@BaseApplication)
            modules(listOf(appModule, networkModule))
        }
    }
}