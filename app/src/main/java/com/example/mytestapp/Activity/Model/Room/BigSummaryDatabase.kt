package com.example.mytestapp.Activity.Model.Room

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteOpenHelper
import com.example.mytestapp.Activity.Model.BigSummaryModel
import com.example.mytestapp.Activity.Util.DATA_BASE_NAME
import com.example.mytestapp.Activity.Util.NUMBERS_OF_THREADS
import java.util.concurrent.Executors

@Database(entities = [(BigSummaryModel::class)], version = 1)
abstract class BigSummaryDatabase() : RoomDatabase() {

    abstract fun bigSummaryDao(): BigSummaryDao
    private var moviesPaged: LiveData<PagedList<BigSummaryModel>>? = null


//    companion object {
//        private val sLock = Any()
//        lateinit var instance: BigSummaryDatabase
//        fun getInstance(context: Application): BigSummaryDatabase {
//            synchronized(sLock) {
//                if (instance == null) {
//                    instance = Room.databaseBuilder(
//                        context.applicationContext,
//                        BigSummaryDatabase::class.java, DATA_BASE_NAME
//                    )
//                        .build()
//                    instance!!.init()
//
//                }
//                return instance
//            }
//        }
//    }


     fun init() {
        val pagedListConfig = PagedList.Config.Builder().setEnablePlaceholders(false)
            .setInitialLoadSizeHint(Integer.MAX_VALUE).setPageSize(Integer.MAX_VALUE).build()
        val executor = Executors.newFixedThreadPool(NUMBERS_OF_THREADS)
        val dataSourceFactory = DBBigSummaryDataSourceFactory(bigSummaryDao())
        val livePagedListBuilder = LivePagedListBuilder(dataSourceFactory, pagedListConfig)
        moviesPaged = livePagedListBuilder.setFetchExecutor(executor).build()
    }

    fun getBigSummaryModels(): LiveData<PagedList<BigSummaryModel>>? {
        return moviesPaged
    }

}