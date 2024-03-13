package com.asemlab.samples.unittesting.mockk

import com.asemlab.samples.unittesting.Car
import com.asemlab.samples.unittesting.isFast
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import org.junit.Assert
import org.junit.Test

class ExtensionFunTest {

    @Test
    fun isFast() {

        // TODO Import where extension function is defined
        mockkStatic("com.asemlab.samples.unittesting.UtilsKt")

        // TODO Mock extension function
        every { any<Car>().isFast() } returns true


        val car = mockk<Car>()
        val car2 = mockk<Car>()

        Assert.assertEquals(true, car.isFast())
        Assert.assertNotEquals(false, car2.isFast())

    }


}