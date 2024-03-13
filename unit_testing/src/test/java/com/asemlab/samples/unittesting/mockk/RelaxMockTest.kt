package com.asemlab.samples.unittesting.mockk

import com.asemlab.samples.unittesting.Car
import com.asemlab.samples.unittesting.Directions
import io.mockk.mockk
import org.junit.Assert
import org.junit.Test

// TODO Relax Mock allow us to use mocked objects without mocking methods
class RelaxMockTest {


    @Test
    fun driveRelaxedMockToWest() {

        // TODO Create Relaxed Mocked object
        val car = mockk<Car>(relaxed = true)

//         TODO There is no need to define the behavior of the drive method
//        every { car.drive(Directions.WEST) } returns "To west"

        car.drive(Directions.WEST) // returns empty string

        Assert.assertEquals("", car.drive(Directions.WEST))

    }


}