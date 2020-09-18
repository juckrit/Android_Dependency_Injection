package com.example.mytestapp.Activity.di

import com.example.mytestapp.Activity.Adapter.OriginalContentFeedPagedListAdapter
import com.example.mytestapp.Activity.Model.BigSummaryModel
import com.example.mytestapp.Activity.Model.Room.BigSummaryDao
import com.example.mytestapp.Activity.Model.Room.BigSummaryRepository
import com.example.mytestapp.Activity.Model.Room.DBBigSummaryPageKeyedDataSource
import com.example.mytestapp.Activity.ViewModel.OriginalContentFeedViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single {
        BigSummaryRepository(get())
    }
//    single {
//        OriginalContentFeedPagedListAdapter(get()) {
//
//        };
//    }
    viewModel { OriginalContentFeedViewModel(get(), get()) }


}
