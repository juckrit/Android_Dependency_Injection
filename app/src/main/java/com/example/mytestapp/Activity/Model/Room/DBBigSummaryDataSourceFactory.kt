package com.example.mytestapp.Activity.Model.Room

import androidx.paging.DataSource
import com.example.mytestapp.Activity.Model.BigSummaryModel


class DBBigSummaryDataSourceFactory(val bigSummaryDao: BigSummaryDao) :
    DataSource.Factory<Int, BigSummaryModel>() {

    private val bigSummaryModelPageKeyedDataSource = DBBigSummaryPageKeyedDataSource(bigSummaryDao)
    override fun create(): DataSource<Int, BigSummaryModel> {
        return bigSummaryModelPageKeyedDataSource
    }


}
