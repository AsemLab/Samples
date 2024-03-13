package com.asemlab.samples.unittesting.mockk

import com.asemlab.samples.unittesting.Directions
import com.asemlab.samples.unittesting.RacingCar
import io.mockk.every
import io.mockk.mockkObject
import org.junit.Assert
import org.junit.Test

class ObjectMockTest {

    @Test
    fun driveRacingCar_toSouth() {

        // TODO Mocking singleton object
        mockkObject(RacingCar)

        every { RacingCar.drive(Directions.NORTH) } returns "To north"

        Assert.assertEquals("To north", RacingCar.drive(Directions.NORTH))

    }


}