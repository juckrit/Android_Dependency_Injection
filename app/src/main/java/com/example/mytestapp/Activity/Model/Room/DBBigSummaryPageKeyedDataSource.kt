package com.example.mytestapp.Activity.Model.Room

import androidx.paging.PageKeyedDataSource
import com.example.mytestapp.Activity.Model.BigSummaryModel

class DBBigSummaryPageKeyedDataSource(val bigSummaryDao: BigSummaryDao) :
    PageKeyedDataSource<Int, BigSummaryModel>() {

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, BigSummaryModel>) {
        val movies = bigSummaryDao.getMovies()
        if (movies.size != 0) {
            callback.onResult(movies, 1, 2)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, BigSummaryModel>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, BigSummaryModel>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}