package com.example.mytestapp.Activity.Model

class NetworkState(status: Status, msg: String) {


    var mStatus = status
    var mMsg = msg

    enum class Status {
        RUNNING,
        SUCCESS,
        FAILED
    }

    init {
        loadedInstance = LOADED
        loadingInstance = LOADING
    }


    companion object {

        var LOADED = NetworkState(Status.SUCCESS, "Success")
        private var LOADING = NetworkState(Status.RUNNING, "Running")

        private lateinit var loadedInstance: NetworkState
        private lateinit var loadingInstance: NetworkState

        fun getLoadedInstance() = LOADED
        fun getLoadingInstance() = LOADING
    }


}