package com.asemlab.samples.unittesting.mockk

import com.asemlab.samples.unittesting.printAll
import io.mockk.every
import io.mockk.mockkStatic
import io.mockk.verify
import io.mockk.verifyAll
import org.junit.Test

class VarargTest {

    @Test
    fun printAll_anyVararg() {
        mockkStatic("com.asemlab.samples.unittesting.UtilsKt")

        // TODO Mock vararg with anyVararg
        every { printAll(*anyVararg()) } answers { callOriginal() }

        printAll(5, 1.0, 3, 4)

        verify { printAll(5, 1.0, 3, 4) }


    }

    @Test
    fun printAll_customVararg() {
        mockkStatic("com.asemlab.samples.unittesting.UtilsKt")

        every { printAll(*anyVararg()) } answers { callOriginal() }

        // TODO Mock vararg when all the arguments are EVEN
        every { printAll(*varargAll<Int> { it % 2 == 0 }) } answers {
            println("Even")
        }

        // TODO Mock vararg when all the arguments are ODD
        every { printAll(*varargAll<Int> { it % 2 != 0 }) } answers {
            println("Odd")
        }

        printAll(5, 1, 3, 4) // Matches case 1
        printAll(1, 3, 5)    // Matches case 2
        printAll(2, 4, 6)    // Matches case 3

        verifyAll {
            printAll(5, 1, 3, 4)
            printAll(1, 3, 5)
            printAll(2, 4, 6)
        }

    }


}