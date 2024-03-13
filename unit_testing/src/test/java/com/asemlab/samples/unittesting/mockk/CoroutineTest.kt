package com.asemlab.samples.unittesting.mockk

import com.asemlab.samples.unittesting.Car
import io.mockk.coEvery
import io.mockk.coJustAwait
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

class CoroutineTest {


    @Test
    fun getCarDetails() = runTest {
        val car = mockk<Car>()

        // TODO Mock suspend function with coEvery
        coEvery { car.getCarDetails() } returns "No Data!"

        val result = car.getCarDetails()

        // TODO Also you can use coVerifyAll/Order/Sequence
        coVerify { car.getCarDetails() }

        Assert.assertEquals("No Data!", result)


    }

    @Test
    fun printCarOnlineStatus() = runTest {
        val car = mockk<Car>()

        // TODO Mock suspend function with coEvery
        coJustAwait { car.printOnlineStatus() } // printOnlineStatus will never return

        val job = launch(UnconfinedTestDispatcher()) {
            car.printOnlineStatus()
        }

        coVerify { car.printOnlineStatus() }

        job.cancelAndJoin()

    }

}