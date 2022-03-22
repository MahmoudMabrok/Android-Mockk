package com.android.mvppattern.ui

import com.android.mvppattern.data.DataRepository
import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK
import junit.framework.Assert.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test

class MockPresenterExceptionTest {

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
    fun fetchDataWithAnException() {
        every { dataRepository.fetchData() } throws IllegalStateException("Hi we have a problem")
        mainPresenter.fetchData()

        val captureErrorData = slot<Throwable>()


        verify(exactly = 0) { view.onResult(any()) }
        verify(exactly = 1) { view.onError(capture(captureErrorData)) }

        captureErrorData.captured.let { throwable ->
            assertEquals("Hi we have a problem", throwable.message)
        }
    }

    @After
    fun tearDown() {
        unmockkAll()
    }
}