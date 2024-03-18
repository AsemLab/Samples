package com.asemlab.samples.unittesting.robolectric

import android.content.Context
import android.graphics.Color
import com.asemlab.samples.unittesting.R
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
class QualifiedResTest {

    lateinit var context: Context

    @Before
    fun setUp() {
        context = RuntimeEnvironment.getApplication().baseContext
    }

    @Test
    fun getGreetingString_for_default() {

        val greeting = context.getString(R.string.greeting)

        Assert.assertEquals("Hello", greeting)

    }

    // TODO Use resources qualifiers with Robolectric
    @Test
    @Config(qualifiers = "es")
    fun getGreetingString_for_spanish() {

        val greeting = context.getString(R.string.greeting)

        Assert.assertEquals("Hola", greeting)

    }

    @Test
    fun getMainBackgroundColor_for_lightMode() {

        val mainBackground = context.getColor(R.color.main_background)

        Assert.assertEquals(Color.WHITE, mainBackground)
    }

    @Test
    @Config(qualifiers = "night")
    fun getMainBackgroundColor_for_nightMode() {

        val mainBackground = context.getColor(R.color.main_background)

        Assert.assertEquals(Color.BLACK, mainBackground)
    }

}