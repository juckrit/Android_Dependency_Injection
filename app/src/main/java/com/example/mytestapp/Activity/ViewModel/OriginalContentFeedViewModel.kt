package com.example.mytestapp.Activity.ViewModel


import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.example.mytestapp.Activity.DataSourceFactory.OriginalContentFeedDataSourceFactory

import com.example.mytestapp.Activity.Model.BigSummaryModel
import com.example.mytestapp.Activity.Model.NetworkState
import com.example.mytestapp.Activity.Model.Room.BigSummaryRepository

class OriginalContentFeedViewModel(private val context: Application, private val repository : BigSummaryRepository) : ViewModel() {

//    //creating livedata for PagedList  and PagedKeyedDataSource
//    var itemPagedList: LiveData<PagedList<BigSummaryModel>>
//    var liveDataSource: LiveData<PageKeyedDataSource<Int, BigSummaryModel>>
//
//
//
//    //constructor
//    init {
//        //getting our data source factory
//        val itemDataSourceFactory = OriginalContentFeedDataSourceFactory()
//
//        //getting the live data source from data source factory
//        liveDataSource = itemDataSourceFactory.sourceLiveData
//
//        //Getting PagedList config
//        val pagedListConfig = PagedList.Config.Builder()
//                .setEnablePlaceholders(false)
//                .setInitialLoadSizeHint(20)
//                .setPageSize(20).build()
//
//        //Building the paged list
//        itemPagedList = LivePagedListBuilder(itemDataSourceFactory, pagedListConfig)
//                .build()
//
//    }

//    var repository = BigSummaryRepository(context)

    fun getBigSummaryModel(): LiveData<PagedList<BigSummaryModel>> {
        return repository.getBigSummaryModel()
    }

    fun getNetworkState(): LiveData<NetworkState> {
        return repository.getNetworkState()
    }


}


