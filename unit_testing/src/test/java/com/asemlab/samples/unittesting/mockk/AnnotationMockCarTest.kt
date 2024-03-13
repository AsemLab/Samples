package com.asemlab.samples.unittesting.mockk

import com.asemlab.samples.unittesting.Car
import com.asemlab.samples.unittesting.Directions
import io.mockk.MockKAnnotations
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.impl.annotations.SpyK
import io.mockk.verify
import org.junit.Assert
import org.junit.Before
import org.junit.Test


class AnnotationMockCarTest {

    // TODO Mock using Annotation
    @MockK/*(relaxed = true, relaxUnitFun = true)*/
    lateinit var car: Car

    @SpyK
    var carSpy = Car(50, Directions.NORTH)

    @RelaxedMockK
    lateinit var carRelax: Car

    // TODO Init mocked objects with annotations
    @Before
    fun setUp() = MockKAnnotations.init(this/*, relaxed = true*/) // TODO Make all the mock objects relaxed


    @Test
    fun mockDirection_getDirection_south() {

        every { car.direction } returns Directions.SOUTH

        Assert.assertEquals(Directions.SOUTH, car.direction)

    }

    @Test
    fun verifyDriveOnSpy_called_true() {

        carSpy.drive(Directions.NORTH) // returns whatever the real function of Car returns

        verify { carSpy.drive(Directions.NORTH) }

        confirmVerified(carSpy)
    }

    @Test
    fun driveRelaxedMockToWest() {

        carRelax.drive(Directions.WEST) // returns empty string

        Assert.assertEquals("", carRelax.drive(Directions.WEST))

    }


}