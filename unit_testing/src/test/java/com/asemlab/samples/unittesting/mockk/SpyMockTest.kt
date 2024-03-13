package com.asemlab.samples.unittesting.mockk

import com.asemlab.samples.unittesting.Car
import com.asemlab.samples.unittesting.Directions
import io.mockk.confirmVerified
import io.mockk.spyk
import io.mockk.verify
import org.junit.Test

class SpyMockTest {


    @Test
    fun verifyDriveOnSpy_called_true() {

        // TODO Spy allows you to use real object and behavior of the class
        val car2 = spyk(Car(50, Directions.EAST)) // or spyk<Car>() to call the default constructor

        car2.drive(Directions.NORTH) // returns whatever the real function of Car returns

        verify { car2.drive(Directions.NORTH) }

        confirmVerified(car2)
    }



}