package com.asemlab.samples.unittesting.robolectric

import android.content.Context
import com.asemlab.samples.unittesting.R
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config


@RunWith(RobolectricTestRunner::class)
class ConfigTest {

    lateinit var context: Context

    @Before
    fun setUp() {
        context = RuntimeEnvironment.getApplication().baseContext
    }

    // TODO Add Configuration for Robolectric
    @Test
    @Config(sdk = [26])
    fun getString_for_sdk26() {

        val androidSdk = context.getString(R.string.android_sdk)

        Assert.assertEquals("34", androidSdk)

    }

    @Test
    @Config(sdk = [30])
    fun getString_for_sdk30() {

        val androidSdk = context.getString(R.string.android_sdk)

        Assert.assertEquals("30", androidSdk)

    }

    @Test
    @Config(sdk = [31])
    fun getString_for_sdk31() {

        val androidSdk = context.getString(R.string.android_sdk)

        Assert.assertEquals("30", androidSdk)

    }

}