package com.asemlab.samples.unittesting.mockk

import com.asemlab.samples.unittesting.Car
import com.asemlab.samples.unittesting.Directions
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert
import org.junit.Test


class SimpleMockCarTest {
    @Test
    fun mockDirection_getDirection_south() {

        // TODO Mock object
        val car = mockk<Car>()

        // TODO Change the behavior that is going to be mocked
        every { car.drive(Directions.SOUTH) } returns "You drive to the south"

        Assert.assertEquals("You drive to the south", car.drive(Directions.SOUTH))

    }

    @Test
    fun verifyGetDirection_called_true() {

        val car = mockk<Car>()
        every { car.drive(Directions.NORTH) } returns "You drive to the north"

        // TODO Call the behavior
        car.drive(Directions.NORTH)

        // TODO Verifies that the mocked behavior was called
        verify { car.drive(Directions.NORTH) }

        confirmVerified(car)
    }

    // This test should fail
    @Test
    fun verifyGetDirection_called_false() {

        val car = mockk<Car>()
        every { car.drive(Directions.SOUTH) } returns "You drive to the south"

        verify { car.drive(Directions.SOUTH) }

        confirmVerified(car)
    }

    @Test
    fun printStatus_mockUnitBehavior() {

        val car = mockk<Car>()
        every { car.printStatus() } answers {
            println("The car is moving")
        }

        car.printStatus()

        verify { car.printStatus() }

        confirmVerified(car)

    }

    @Test
    fun printStatus_mockBehavior() {

        val car = mockk<Car>()
        every { car.drive(Directions.WEST) } answers {
            "The car is moving"
        }

        val s = car.drive(Directions.WEST)

        verify { car.drive(Directions.WEST) }

        confirmVerified(car)

        Assert.assertEquals("The car is moving", s)
    }

    @Test
    fun getDirection_mockAnyParameter() {

        val car = mockk<Car>()
        val message = "The car is moving"

        // TODO Use any() to mock function call with any parameters
        every { car.drive(any()) } answers {
            message
        }

        val toWest = car.drive(Directions.WEST)
        val toEast = car.drive(Directions.EAST)
        val toSouth = car.drive(Directions.SOUTH)


        Assert.assertEquals(message, toWest)
        Assert.assertEquals(message, toEast)
        Assert.assertEquals(message, toSouth)

    }

}