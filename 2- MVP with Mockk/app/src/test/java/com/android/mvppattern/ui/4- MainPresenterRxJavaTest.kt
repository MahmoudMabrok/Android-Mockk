package com.android.mvppattern.ui

import com.android.mvppattern.data.DataRepository
import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK
import io.reactivex.rxjava3.core.Single
import junit.framework.Assert.assertNotNull
import org.junit.After
import org.junit.Before
import org.junit.Test

class MainPresenterRxJavaTest {

    @RelaxedMockK
    lateinit var view: MainContract.View

    @RelaxedMockK
    lateinit var dataRepository: DataRepository

    lateinit var mainPresenter: MainContract.Presenter

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        mainPresenter = MainPresenter(view, dataRepository)
    }

    @Test
    fun fetchDataViaRXWithAnEmptyResult() {
        every { dataRepository.fetchDataWithRX() } returns Single.just(listOf())
        mainPresenter.fetchDataViaRX()

        val captureData = slot<List<UiDataModel>>()

        verify(exactly = 1) { view.onResult(capture(captureData)) }
        captureData.captured.let { res ->
            assertNotNull(res)
            assert(res.isEmpty())
        }
    }

    @Test
    fun fetchDataViaRXWithAnException() {
        every { dataRepository.fetchDataWithRX() } returns Single.error(Throwable("Oh oh something is broken"))
        mainPresenter.fetchDataViaRX()

        verify(exactly = 0) { view.onResult(any()) }
        verify(exactly = 1) { view.onError(any()) }

    }

    @After
    fun tearDown() {
        unmockkAll()
    }
}