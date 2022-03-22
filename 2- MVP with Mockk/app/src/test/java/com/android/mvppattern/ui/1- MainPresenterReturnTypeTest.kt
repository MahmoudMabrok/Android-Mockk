package com.android.mvppattern.ui

import com.android.mvppattern.data.DataRepository
import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class MainPresenterReturnTypeTest {

    /*
    In this first sample we want to:
    Mock a response with an empty list
    Verify onSuccess() is called
    Verify the output is an empty list
     */

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
    fun fetchDataWithAnEmptyResult() {
        every { dataRepository.fetchData() } returns listOf()
        mainPresenter.fetchData()

        val captureData = slot<List<UiDataModel>>()

        verify(exactly = 1) { view.onResult(capture(captureData)) }
        captureData.captured.let { res ->
            Assert.assertNotNull(res)
            assert(res.isEmpty())
        }
    }

    @After
    fun tearDown() {
        unmockkAll()
    }
}