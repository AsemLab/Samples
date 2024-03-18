package com.asemlab.samples.unittesting.robolectric

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.asemlab.samples.unittesting.R
import com.asemlab.samples.unittesting.ui.MainActivity
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
class ViewTest {


    @Test
    fun changeTitle_onClick() {

        val activity = Robolectric.buildActivity(MainActivity::class.java).setup()

        // TODO Use Espresso with Robolectric without needing real device
        onView(withId(R.id.titleTV)).check(matches(withText("Hello")))

        onView(withId(R.id.button)).perform(click())

        onView(withText(R.string.new_title)).check(matches(isDisplayed()))

    }

    @Test
    @Config(qualifiers = "es")
    fun checkTitle_inSpanish() {

        // TODO Another way to init MainActivity
        val scenario = ActivityScenario.launch(MainActivity::class.java)

            onView(withId(R.id.titleTV)).check(matches(withText("Hola")))
    }

}