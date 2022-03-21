package com.android.mockkbasics

import io.mockk.*
import junit.framework.TestCase.assertEquals
import org.junit.Test

class BehaviorVerificationExample {

    /*
      Methods to verify statements:
      1- verify{} -  It verifies unordered a verification that a call performed.
      2- verifyAll{} - verifies that all calls happened without checking their order.
      3- verifySequence{} - verifies that the calls happened in a specified sequence.
      4- verifyOrder{} - verifies that calls happened in a specific order.
     */

    @Test
    fun verifyPropertiesCall() {
        val doc1 = mockk<Dependency1>()
        val doc2 = mockk<Dependency2>()

        every { doc1.value1 } returns 5
        every { doc2.value2 } returns "6"

        val sut = SystemUnderTest(doc1, doc2)

        assertEquals(11, sut.calculate())

        verify {
            doc1.value1
            doc2.value2
        }
    }

    // There are basically four types of verification supported: unordered, ordered, sequential and all.

    /*
      Unordered verification guarantees that calls were happening without regard to order.
      For example, you would like to check if call(5) happened at least once.
     */

    @Test
    fun verifyUnorderedCall() {
        val doc1 = mockk<Dependency1>()

        every { doc1.call(1) } returns 1

        doc1.call(1)

        verify { doc1.call(1) }

        // Unlike in every, in verify block, you can have several calls going one after another.

        every { doc1.call(2) } returns 2
        every { doc1.call(3) } returns 3
        doc1.call(2)
        doc1.call(3)
        verify {
            doc1.call(2)
            doc1.call(3)
        }

        // The above code checks that call(2) and call(3) happened at least once.

        /*
          verify can have parameters. atLeast, atMost specify how much verified calls happening.
          By default, atLeast is 1 and atMost is Int.MAX_VALUE which effectively means we expect call happen at least once.
         */

        every { doc1.call(4) } returns 4
        doc1.call(4)
        doc1.call(4)
        doc1.call(4)
        verify(atLeast = 2, atMost = 7) {
            doc1.call(4)
        }

        // The above code checks that call(4) happened at least 2 and at most 7 times.

        // exactly parameter sets both atLeast and atMost to the same value.

        every { doc1.call(5) } returns 5
        doc1.call(5)
        verify(exactly = 1) {
            doc1.call(5)
        }

        // The above code checks call(5) happened exactly 1 time.

        // Specifying exactly = 0 you can verify call did not happen at all:

        verify(exactly = 0) {
            doc1.call(6)
        }

        /*
         Sometimes you want to check if mocked DOC was not called at all.
         This can be achieved with special wasNot Called construct.
         It is equivalent to verifyZeroInteractions in Mockito.
         */
        val doc2 = mockk<Dependency2>()
        verify {
            doc2 wasNot Called
        }

        // All the examples are basically for unordered verification.
    }


    /*
     verifyAll is doing same as verify, except it additionally checks that all matched calls are the only
     calls happened to mentioned mocks. This is similar to verifyNoMoreInteractions in Mockito.
     */
    @Test
    fun verifyAll() {
        val doc1 = mockk<Dependency1>(relaxed = true)

        doc1.call(1)
        doc1.call(2)

        verifyAll {
            doc1.call(1)
            doc1.call(2)
        }
        // verifyAll is usefull when we have function call with different parameters
        // The difference between verify and verifyAll is verifyAll will check all the functions calls with  different parameter inputs

        /*
        verifyAll is doing same as verify, except it additionally checks that all matched calls are the only
        calls happened to mentioned mocks.

        consider this example for difference
          val doc1 = mockk<Dependency1>(relaxed = true)

           doc1.call(1)
           doc1.call(2)
           doc1.call(3)

        verifyAll {
            doc1.call(1)
            doc1.call(2)
        }
         The above code give us exception because  doc1.call(3) is missing in verifyAll block

         verify {
            doc1.call(1)
            doc1.call(2)
        }

        The above code will work fine because verify will only check the function call which is mention inside its block
         */

    }


    /*
     verifySequence checks order and that all the matched calls are the only calls happened to mentioned mocks.
     */
    @Test
    fun verifySequence() {
        val doc1 = mockk<Dependency1>(relaxed = true)

        doc1.call(1)
        doc1.call(2)
        doc1.call(3)

        verifySequence {
            doc1.call(1)
            doc1.call(2)
            doc1.call(3)
        }
    }

    /*
     verifyOrder on other hand verifies only the order of calls that happened, allowing gaps in the sequence of calls.
     */
    @Test
    fun verifyOrder() {
        val doc1 = mockk<Dependency1>(relaxed = true)

        doc1.call(1)
        doc1.call(2)
        doc1.call(3)
        doc1.call(4)

        verifyOrder {
            doc1.call(1)
            // we can skip the doc1.call(2) call here as verifyOrder verifies only the order also allowing gaps in the sequence of calls.
            doc1.call(3)

            doc1.call(4)
        }
    }



}