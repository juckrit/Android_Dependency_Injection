package com.example.mytestapp.Activity.Api

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.mytestapp.Activity.DataSourceFactory.OriginalContentFeedDataSourceFactory
import com.example.mytestapp.Activity.Model.BigSummaryModel
import com.example.mytestapp.Activity.Util.LOADING_PAGE_SIZE
import com.example.mytestapp.Activity.Util.NUMBERS_OF_THREADS
import java.util.concurrent.Executors

class SummaryNetwork(
    dataSourceFactory: OriginalContentFeedDataSourceFactory,
    boundaryCallback: PagedList.BoundaryCallback<BigSummaryModel>
) {
    private val moviesPaged: LiveData<PagedList<BigSummaryModel>>
//    private val networkState: LiveData<NetworkState>


    init {
        val pagedListConfig = PagedList.Config.Builder().setEnablePlaceholders(false)
            .setInitialLoadSizeHint(LOADING_PAGE_SIZE).setPageSize(LOADING_PAGE_SIZE).build()

//        dataSourceFactory.getNetworkState(),
//        { OriginalContentFeedKeyedDataSource::getNetworkState } as Function<OriginalContentFeedKeyedDataSource<LiveData<NetworkState>>

//        networkState = Transformations.switchMap(dataSourceFactory.getNetworkState(),OriginalContentFeedKeyedDataSource::getNetworkState as Function<NetworkState, LiveData<NetworkState>> )

        val executor = Executors.newFixedThreadPool(NUMBERS_OF_THREADS)

        val livePagedListBuilder = LivePagedListBuilder(dataSourceFactory, pagedListConfig)

        moviesPaged =
            livePagedListBuilder.setFetchExecutor(executor).setBoundaryCallback(boundaryCallback)
                .build()
    }

    fun getPagedMovies(): LiveData<PagedList<BigSummaryModel>> {
        return moviesPaged
    }


//    fun getNetworkState(): LiveData<NetworkState> {
//        return networkState
//    }

}