package com.example.mytestapp.Activity.DataSourceFactory

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.example.mytestapp.Activity.DataSource.OriginalContentFeedKeyedDataSource
import com.example.mytestapp.Activity.Model.BigSummaryModel
import com.example.mytestapp.Activity.Model.NetworkState
import rx.subjects.ReplaySubject

class OriginalContentFeedDataSourceFactory() : DataSource.Factory<Int, BigSummaryModel>() {
    var sourceLiveData = MutableLiveData<PageKeyedDataSource<Int, BigSummaryModel>>()
    val source = OriginalContentFeedKeyedDataSource()


    override fun create(): DataSource<Int, BigSummaryModel> {

        sourceLiveData.postValue(source)
        return source
    }

    fun getNetworkState(): LiveData<NetworkState> {
        return source.getNetworkState()
    }

    fun getMovies(): ReplaySubject<BigSummaryModel> {
        return source.getBigSummaryModel()
    }


}