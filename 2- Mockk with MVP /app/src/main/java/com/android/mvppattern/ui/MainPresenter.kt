package com.android.mvppattern.ui

import com.android.mvppattern.data.DataRepository
import com.android.utils.MyUselessUtils
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
import java.util.*

// This is the presenter:
class MainPresenter(
    private val view: MainContract.View,
    private val dataRepository: DataRepository
) : MainContract.Presenter {

    override fun fetchData() {
        try {
            val result = dataRepository.fetchData()

            view.onResult(result.map { UiDataModel(UUID.randomUUID().toString(), it.id, it.value) })
        } catch (err: Exception) {
            view.onError(err)
        }
    }

    /*
     It’s quite simple, after fetchData() we make three things: fetch, map and pass the result to the consumer view.
    */

    override fun fetchDataViaCallback() {
        try {
            dataRepository.fetchDataViaCallBack { data ->
                view.onResult(data.map {
                    UiDataModel(
                        UUID.randomUUID().toString(),
                        it.id,
                        it.value
                    )
                })
            }
        } catch (err: Exception) {
            view.onError(err)
        }
    }

    override fun fetchDataViaRX() {
        dataRepository.fetchDataWithRX()
            .flatMap { result ->
                val output =
                    result.map { UiDataModel(UUID.randomUUID().toString(), it.id, it.value) }
                Single.just(output)
            }
            .subscribe(object : SingleObserver<List<UiDataModel>> {
                override fun onSuccess(t: List<UiDataModel>) {
                    view.onResult(t)
                }

                override fun onSubscribe(d: Disposable) {}

                override fun onError(e: Throwable) {
                    view.onError(e)
                }
            })
    }

    override fun fetchDataWithStaticUUID() {
        try {
            val result = dataRepository.fetchData()
            view.onResult(result.map {
                UiDataModel(
                    MyUselessUtils.generateUUID(),
                    it.id,
                    it.value
                )
            })
        } catch (err: Throwable) {
            view.onError(err)
        }
    }

    override fun fetchDataWithLog() {
        try {
            val result = dataRepository.fetchData()
            log("fetchData")
            view.onResult(result.map { UiDataModel(UUID.randomUUID().toString(), it.id, it.value) })
        } catch (err: Exception) {
            view.onError(err)
        }
    }

    override fun log(value: String) {
        // doing something here
    }
}
