//package com.example.mytestapp.Activity.ViewModelFactory
//
//import android.app.Application
//import android.content.Context
//
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.ViewModelProvider
//import com.example.mytestapp.Activity.ViewModel.OriginalContentFeedViewModel
//
//class OriginalContentFeedViewModelFactory(private val mContext: Application) : ViewModelProvider.NewInstanceFactory() {
//
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        return if (modelClass == OriginalContentFeedViewModel::class.java) {
//            OriginalContentFeedViewModel(mContext) as T
//        } else {
//
//            super.create(modelClass)
//        }
//    }
//}
