package com.asemlab.samples.unittesting.mockk

import com.asemlab.samples.unittesting.Car
import com.asemlab.samples.unittesting.Directions
import com.asemlab.samples.unittesting.Motorcycle
import io.mockk.OfTypeMatcher
import io.mockk.every
import io.mockk.mockkConstructor
import org.junit.Assert.assertEquals
import org.junit.Test

class ConstructorMockTest {

    @Test
    fun carConstructor(){

        // TODO Mock constructor
        mockkConstructor(Car::class)

        every { anyConstructed<Car>().drive(Directions.WEST) } returns "To west"

        assertEquals("To west", Car(40, Directions.NORTH).drive(Directions.WEST)) // note new object is created
        assertEquals("To west", Car(100, Directions.WEST).drive(Directions.WEST)) // note new object is created
    }

    @Test
    fun motorcycleConstructor_print(){

        mockkConstructor(Motorcycle::class)

        // TODO Mock the constructor that takes String
        // TODO This will take effect for every object that has created with constructor(String)
        every { constructedWith<Motorcycle>(OfTypeMatcher<String>(String::class)).drive(Directions.WEST) } returns "To west"

        assertEquals("To west", Motorcycle("Toyota").drive(Directions.WEST))// note new object is created
        assertEquals("To west", Motorcycle("Honda").drive(Directions.WEST))// note new object is created
    }

}