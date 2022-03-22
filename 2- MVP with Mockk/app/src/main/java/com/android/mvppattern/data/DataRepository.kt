package com.android.mvppattern.data

import com.android.mvppattern.domain.DataModel
import io.reactivex.rxjava3.core.Single

class DataRepository {

    fun fetchData(): List<DataModel> {
        return listOf(
            DataModel(1, "Value 1"), DataModel(2, "Value 2"),
            DataModel(3, "Value 3"), DataModel(4, "Value 4")
        )
    }

    fun fetchDataViaCallBack(callback: (data: List<DataModel>) -> Unit) {
        val list = listOf(
            DataModel(1, "Value 1"), DataModel(2, "Value 2"),
            DataModel(3, "Value 3"), DataModel(4, "Value 4")
        )
        callback.invoke(list)
    }

    fun fetchDataWithRX(): Single<List<DataModel>> {
        return Single.just(
            listOf(
                DataModel(1, "Value 1"), DataModel(2, "Value 2"),
                DataModel(3, "Value 3"), DataModel(4, "Value 4")
            )
        )
    }
}