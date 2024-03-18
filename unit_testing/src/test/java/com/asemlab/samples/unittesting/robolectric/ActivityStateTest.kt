package com.asemlab.samples.unittesting.robolectric

import com.asemlab.samples.unittesting.ui.MainActivity
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric.buildActivity
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class ActivityStateTest {


    @Test
    fun isStateChanged_toPaused() {
        val activity = buildActivity(MainActivity::class.java).setup()

        activity.pause()

        Assert.assertEquals("Paused", activity.get().state)
    }

    @Test
    fun isStateChanged_toCreated_afterStopped() {
        val activity = buildActivity(MainActivity::class.java).setup()

        activity.pause()
        activity.stop()

        Assert.assertEquals("Stopped", activity.get().state)
    }

    @Test
    fun isStateChanged_toCreated_afterPaused() {
        val activity = buildActivity(MainActivity::class.java).setup()

        activity.pause()

        Assert.assertEquals("Paused", activity.get().state)

        activity.resume()
        activity.recreate()

        Assert.assertEquals("Created", activity.get().state)
    }

}