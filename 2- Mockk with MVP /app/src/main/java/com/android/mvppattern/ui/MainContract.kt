package com.android.mvppattern.ui

/*
This is a Contract interface where we declare the two interfaces for View and Presenter:
 */
interface MainContract {

    interface Presenter {
        fun fetchData()
        fun fetchDataViaCallback()
        fun fetchDataViaRX()
        fun fetchDataWithStaticUUID()
        fun fetchDataWithLog()
        fun log(value: String)
    }

    interface View {
        fun onResult(result: List<UiDataModel>)
        fun onError(error: Throwable)
    }
}