package com.asemlab.samples.unittesting.mockk

import com.asemlab.samples.unittesting.Car
import com.asemlab.samples.unittesting.Motorcycle
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

class UnitFunMockTest {


    @Test
    fun printCarStatus_relax() {

        // TODO Relax unit function
        val car = mockk<Car>(relaxUnitFun = true)

        car.printStatus()

        verify { car.printStatus() }

        confirmVerified(car)

    }

    @Test
    fun printMotorcycleStatus() {

        val motorcycle = mockk<Motorcycle>()

        // TODO justRun will not execute the original behaviour( fake call)
        justRun { motorcycle.printStatus() }

        motorcycle.printStatus()

        verify { motorcycle.printStatus() }

        confirmVerified(motorcycle)

    }

}