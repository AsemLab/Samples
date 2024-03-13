package com.asemlab.samples.unittesting.mockk

import com.asemlab.samples.unittesting.Car
import com.asemlab.samples.unittesting.Directions
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert
import org.junit.Test

class PartialMockTest {

    @Test
    fun driveCar_southOrNorth() {

        val car = mockk<Car>()

        // TODO Returns the actual implementation when pass NORTH
        every { car.drive(Directions.NORTH) } answers { callOriginal() }

        // TODO Mock the actual implementation when pass SOUTH
        every { car.drive(Directions.SOUTH) } returns "To the south"

        Assert.assertEquals("To the south", car.drive(Directions.SOUTH) )
        Assert.assertEquals( "Car drives toward NORTH", car.drive(Directions.NORTH) )

    }

}