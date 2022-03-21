package com.android.mvppattern.ui

import com.android.mvppattern.data.DataRepository
import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK
import org.junit.After
import org.junit.Before
import org.junit.Test

class MainPresenterSpyTest {

    /*
    Another common scenario is the necessity to use verify{} to verify if a method inside the testing class is called.
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
    fun log() {
        val spiedPresenter = spyk(mainPresenter)
        every { dataRepository.fetchData() } returns listOf()
        spiedPresenter.fetchDataWithLog()

        verify(exactly = 1) { spiedPresenter.log(any()) }
    }


    @After
    fun tearDown() {
        unmockkAll()
    }
}