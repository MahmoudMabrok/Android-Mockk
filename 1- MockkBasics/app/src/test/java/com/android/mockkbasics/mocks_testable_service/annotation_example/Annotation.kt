package com.android.mockkbasics.mocks_testable_service.annotation_example

import com.android.mockkbasics.InjectTestService
import com.android.mockkbasics.TestableService
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import org.junit.Before

/*
Let’s mock InjectTestService in a test by using annotations:

 */
class Annotation {
    @MockK
    lateinit var service1: TestableService

    @MockK
    lateinit var service2: TestableService

    @InjectMockKs
    var objectUnderTest = InjectTestService()

    @Before
    fun setUp() = MockKAnnotations.init(this)

    // Write your Tests here

    /*
     In the above example, we’ve used the @InjectMockKs annotation.
     This specifies an object where defined mocks should be injected.
     By default, it injects variables that are not assigned yet.
     We can use @OverrideMockKs to override fields that have a value already.
     */
}