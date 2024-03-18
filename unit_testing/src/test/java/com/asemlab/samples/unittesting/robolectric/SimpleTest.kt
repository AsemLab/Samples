package com.asemlab.samples.unittesting.robolectric

import android.content.Context
import com.asemlab.samples.unittesting.R
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

// TODO Simple test with Robolectric
@RunWith(RobolectricTestRunner::class)
class SimpleTest {

    lateinit var context: Context

    @Before
    fun setUp() {
        // TODO Get Context
        context = RuntimeEnvironment.getApplication().baseContext // You can cast this to your app class
    }

    @Test
    fun onClick() {
        Assert.assertEquals("UnitTesting", context.getString(R.string.app_name))
    }

}