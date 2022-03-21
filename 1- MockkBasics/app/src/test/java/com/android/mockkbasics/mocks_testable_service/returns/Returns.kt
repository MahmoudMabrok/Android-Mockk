package com.android.mockkbasics.mocks_testable_service.returns

import com.android.mockkbasics.Dependency1
import com.android.mockkbasics.Dependency2
import com.android.mockkbasics.SystemUnderTest
import io.mockk.*
import junit.framework.Assert.assertEquals
import org.junit.Test

class Returns {

    /*
    returnsMany specify a number of values that are used one by one
    i.e. first matched call returns first element, second — second element, e.t.c.
     */
    @Test
    fun calculateAddsValues() {
        val doc1 = mockk<Dependency1>()
        val doc2 = mockk<Dependency2>()

        every { doc1.value1 } returnsMany  listOf(1, 2, 3)
        every { doc2.value2 } returns "6"

        val sut = SystemUnderTest(doc1, doc2)

        assertEquals(7, sut.calculate())
        assertEquals(8, sut.calculate())
        assertEquals(9, sut.calculate())
    }

    // You can achieve the same using andThen construct:

    @Test
    fun calculateAddsValuesAndThen() {
        val doc1 = mockk<Dependency1>()
        val doc2 = mockk<Dependency2>()

        every { doc1.value1 } returns 1 andThen 2 andThen 3
        every { doc2.value2 } returns "6"

        val sut = SystemUnderTest(doc1, doc2)

        assertEquals(7, sut.calculate())
        assertEquals(8, sut.calculate())
        assertEquals(9, sut.calculate())
    }


    @Test(expected = RuntimeException::class)
    fun returnException() {
        val doc1 = mockk<Dependency1>()

        every { doc1.call(5) } throws RuntimeException("error happened")

        doc1.call(5)
    }

    /* just Runs should be used in case return value is Unit.
      just run is used for methods returning Unit (i.e., not returning a value) on strict mocks.
      If you create a mock that is not relaxed and invoke a method on it that has not being stubbed with an every block,
      MockK will throw an exception.
    */
    @Test
    fun justReturns() {
        val doc1 = mockk<Dependency1>()

        every { doc1.result(5) } just Runs

        doc1.result(5)

        verify (exactly = 1){doc1.result(5) }
    }


}