package com.android.mvppattern.ui

import com.android.mvppattern.data.DataRepository
import com.android.mvppattern.domain.DataModel
import com.android.utils.MyUselessUtils
import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK
import junit.framework.Assert.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test

class MainPresenterStaticTest {

    /*
    If we want to test a class the best practices is to pass all resources we want to mock inside the constructor,
     but sometimes we need to mock a static method inside a object like an utils, third party sdk etc;
     so to have a perfect code coverage we need to write test with different behaviour of these static classes. How?
     Fortunately Mockk has also a couple of methods to mock the static object, look at the example below:
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
    fun fetchDataWithADifferentBehaviourOfUUIDGeneration() {

        val uuid = "my-fake-uuid"
        mockkObject(MyUselessUtils)
        every { MyUselessUtils.generateUUID() } returns uuid

        every { dataRepository.fetchData() } returns listOf(DataModel(1, "Value"))
        mainPresenter.fetchDataWithStaticUUID()

        val captureData = slot<List<UiDataModel>>()

        verify(exactly = 1) { view.onResult(capture(captureData)) }
        captureData.captured.let { res ->
            assert(res.isNotEmpty())
            assertEquals(uuid, res.first().uuid)
        }
        unmockkObject(MyUselessUtils)
    }


    @After
    fun tearDown() {
        unmockkAll()
    }
}