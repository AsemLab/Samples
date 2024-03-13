package com.asemlab.samples.unittesting.mockk

import com.asemlab.samples.unittesting.Directions
import io.mockk.every
import io.mockk.mockkObject
import org.junit.Assert
import org.junit.Test

class EnumMockTest {

    @Test
    fun getDirectionName_toLowerCase(){

        // TODO Mock enum object
        mockkObject(Directions.NORTH)

        every { Directions.NORTH.angel } returns 100

        Assert.assertEquals(100, Directions.NORTH.angel)

        Assert.assertEquals(180, Directions.WEST.angel)

    }

}