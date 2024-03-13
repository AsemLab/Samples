package com.asemlab.samples.unittesting.mockk

import com.asemlab.samples.unittesting.Car
import com.asemlab.samples.unittesting.Directions
import io.mockk.MockKAnnotations
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import io.mockk.verifyAll
import io.mockk.verifyOrder
import io.mockk.verifySequence
import org.junit.Before
import org.junit.Test

class VerifyOrderTest {

    @MockK
    lateinit var car: Car

    @Before
    fun setUp() = MockKAnnotations.init(this)

    @Before
    fun mockDriveTo() {
        every { car.drive(any()) } answers { callOriginal() }
    }

    @Test
    fun verifyAll_withoutOrder() {

        car.drive(Directions.NORTH)
        car.drive(Directions.EAST)
        car.drive(Directions.SOUTH)
        car.drive(Directions.WEST)

        verifyAll {
            car.drive(Directions.EAST)
            car.drive(Directions.SOUTH)
            car.drive(Directions.WEST)
            car.drive(Directions.NORTH)
        }

        confirmVerified(car)

    }


    @Test
    fun verifyAll_withOrder() {

        car.drive(Directions.NORTH)
        car.drive(Directions.EAST)
        car.drive(Directions.SOUTH)
        car.drive(Directions.WEST)

        // TODO verifyOrder will fail
        verifyOrder {
            car.drive(Directions.EAST)
            car.drive(Directions.SOUTH)
            car.drive(Directions.WEST)
            car.drive(Directions.NORTH)
        }

        confirmVerified(car)

    }

    @Test
    fun verifyAll_withSequence() {

        car.drive(Directions.NORTH)
        car.drive(Directions.EAST)
        car.drive(Directions.SOUTH)
        car.drive(Directions.WEST)

        // TODO verifySequence will fail because it is missing the last call(West)
        verifySequence {
            car.drive(Directions.NORTH)
            car.drive(Directions.EAST)
            car.drive(Directions.SOUTH)
        }

        confirmVerified(car)

    }


}