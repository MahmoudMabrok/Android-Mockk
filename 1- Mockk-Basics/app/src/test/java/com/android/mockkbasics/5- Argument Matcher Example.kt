package com.android.mockkbasics

import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import org.junit.Test

class ArgumentMatcherTest {
    @Test
    fun calculateAddsValues() {
        val doc1 = mockk<Dependency1>()
        val doc2 = mockk<Dependency2>()

        every { doc1.value1 } returns 5
        every { doc2.value2 } returns "6"

        val sut = SystemUnderTest(doc1, doc2)

        assertEquals(11, sut.calculate())
    }

    // Argument matching example

    @Test
    fun calculateAddsValuesArgumentMatching() {
        val doc1 = mockk<Dependency1>()
        val doc2 = mockk<Dependency2>()

        every { doc1.call(more(5)) } returns 1

        // If you leave matcher a fixed value it is automatically wrapped in eq matcher.
        // every { mock.call(less(5), 5) } returns -1   OR similar like below

        every { doc1.call(or(less(5), eq(5))) } returns -1

        every { doc2.call(2) } returns 2

        val sut = SystemUnderTest(doc1, doc2)

        assertEquals(1, sut.calculateWithCall(3, 2))
    }

    // more, less, eq and or are argument matchers.
}