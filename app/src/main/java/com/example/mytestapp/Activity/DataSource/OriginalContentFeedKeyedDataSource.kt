package com.example.mytestapp.Activity.DataSource

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.example.mytestapp.Activity.Api.ContentApi
import com.example.mytestapp.Activity.Model.ApiResultModel
import com.example.mytestapp.Activity.Model.BigSummaryModel
import com.example.mytestapp.Activity.Model.NetworkState
import com.example.mytestapp.Activity.ViewModel.OriginalContentFeedViewModel
import org.koin.java.KoinJavaComponent.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import rx.subjects.ReplaySubject
import java.util.function.Consumer
import org.koin.android.ext.android.inject


class OriginalContentFeedKeyedDataSource() : PageKeyedDataSource<Int, BigSummaryModel>() {

    var bigSummaryObservable = ReplaySubject.create<BigSummaryModel>();

    val errorMessage = "fail"
    var lastSummaryId = 0
    lateinit var callGetFeed: Call<ApiResultModel>
    private var networkState = MutableLiveData<NetworkState>();

    fun getNetworkState(): LiveData<NetworkState> {
        return networkState
    }

    fun getBigSummaryModel(): ReplaySubject<BigSummaryModel> {
        return bigSummaryObservable
    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, BigSummaryModel>
    ) {
        networkState.postValue(NetworkState.getLoadingInstance())

        callGetFeed = ContentApi.contentApi.getOriginalContentSummariesModel(lastSummaryId)
        callGetFeed.enqueue(object : Callback<ApiResultModel> {
            override fun onFailure(call: Call<ApiResultModel>, t: Throwable) {
                networkState.postValue(NetworkState(NetworkState.Status.FAILED, errorMessage!!))
                callback.onResult(mutableListOf(), 1, 2);

            }

            override fun onResponse(
                call: Call<ApiResultModel>,
                response: Response<ApiResultModel>
            ) {
                if (response.isSuccessful) {
                    if (response.body() != null) {
                        val result = response.body()
                        networkState.postValue(NetworkState.getLoadedInstance())
                        response.body()!!.summaries?.forEach { it ->

                                val a = it
                                bigSummaryObservable.onNext(it)

                        }
                        callback.onResult(
                            result?.summaries!!,
                            null,
                            result?.summaries?.get(result?.summaries?.size?.minus(1))?.id
                        )

                    }
                    networkState.postValue(NetworkState(NetworkState.Status.FAILED, errorMessage!!))

                }
                networkState.postValue(NetworkState(NetworkState.Status.FAILED, errorMessage!!))

            }
        })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, BigSummaryModel>) {
        networkState.postValue(NetworkState.getLoadingInstance())

        callGetFeed = ContentApi.contentApi.getOriginalContentSummariesModel(params.key)
        callGetFeed.enqueue(object : Callback<ApiResultModel> {
            override fun onFailure(call: Call<ApiResultModel>, t: Throwable) {
                networkState.postValue(NetworkState(NetworkState.Status.FAILED, errorMessage!!))

            }

            override fun onResponse(
                call: Call<ApiResultModel>,
                response: Response<ApiResultModel>
            ) {
                if (response.isSuccessful) {
                    if (response.body() != null) {
                        val result = response.body()
                        if (result?.summaries?.size!! > 0) {
                            networkState.postValue(NetworkState.getLoadedInstance())
                            response.body()!!.summaries?.forEach { it ->

                                    val a = it
                                    bigSummaryObservable.onNext(it)

                            }

                            callback.onResult(
                                result?.summaries!!,
                                result?.summaries?.get(result?.summaries?.size?.minus(1))?.id
                            )

                        }


                    }
                    networkState.postValue(NetworkState(NetworkState.Status.FAILED, errorMessage!!))

                }
                networkState.postValue(NetworkState(NetworkState.Status.FAILED, errorMessage!!))

            }
        })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, BigSummaryModel>) {
        networkState.postValue(NetworkState.getLoadingInstance())

        callGetFeed = ContentApi.contentApi.getOriginalContentSummariesModel(params.key)
        callGetFeed.enqueue(object : Callback<ApiResultModel> {
            override fun onFailure(call: Call<ApiResultModel>, t: Throwable) {
                networkState.postValue(NetworkState(NetworkState.Status.FAILED, errorMessage!!))

            }

            override fun onResponse(
                call: Call<ApiResultModel>,
                response: Response<ApiResultModel>
            ) {
                if (response.isSuccessful) {
                    if (response.body() != null) {
                        val result = response.body()
                        networkState.postValue(NetworkState.getLoadedInstance())
                        response.body()!!.summaries?.forEach { it ->
                            bigSummaryObservable.onNext(it) }

                        callback.onResult(
                            result?.summaries!!,
                            result?.summaries?.get(result?.summaries?.size?.minus(1))?.id
                        )
                    }
                    networkState.postValue(NetworkState(NetworkState.Status.FAILED, errorMessage!!))

                }
                networkState.postValue(NetworkState(NetworkState.Status.FAILED, errorMessage!!))

            }
        })
    }


}