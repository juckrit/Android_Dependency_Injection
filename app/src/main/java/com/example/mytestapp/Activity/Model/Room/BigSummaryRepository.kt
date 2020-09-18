package com.example.mytestapp.Activity.Model.Room

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.example.mytestapp.Activity.Api.SummaryNetwork
import com.example.mytestapp.Activity.BaseApplication
import com.example.mytestapp.Activity.DataSourceFactory.OriginalContentFeedDataSourceFactory
import com.example.mytestapp.Activity.Model.BigSummaryModel
import com.example.mytestapp.Activity.Model.NetworkState
import rx.schedulers.Schedulers

class BigSummaryRepository(val context: Context) {

    private val network: SummaryNetwork
    //    val database = BigSummaryDatabase.getInstance(context.applicationContext)
    val database = BaseApplication.getDBInstance()
    val dataSourceFactory = OriginalContentFeedDataSourceFactory()
    val liveDataMerger = MediatorLiveData<PagedList<BigSummaryModel>>()
    var boundaryCallback = object : PagedList.BoundaryCallback<BigSummaryModel>() {
        override fun onZeroItemsLoaded() {
            super.onZeroItemsLoaded()
            val data = database.getBigSummaryModels()!!
            liveDataMerger.addSource(data) { value ->
                liveDataMerger.setValue(value)

                liveDataMerger.removeSource(database.getBigSummaryModels()!!)
            }
        }
    }

    init {


        // when we get new movies from net we set them into the database


        // save the movies into db
        dataSourceFactory.getMovies().observeOn(Schedulers.io()).subscribe { movie ->
            run {
                database.bigSummaryDao().insertMovie(movie)
                val a = database.bigSummaryDao().getMovies()
                val size = a.size
            }
        }


        network = SummaryNetwork(dataSourceFactory, boundaryCallback)
        liveDataMerger.addSource(network.getPagedMovies()) { value ->
            liveDataMerger.setValue(value)
        }
    }


    fun getBigSummaryModel(): LiveData<PagedList<BigSummaryModel>> {
        return liveDataMerger
    }

    fun getNetworkState(): LiveData<NetworkState> {
        var networkState = MutableLiveData<NetworkState>();
        return networkState

    }


}