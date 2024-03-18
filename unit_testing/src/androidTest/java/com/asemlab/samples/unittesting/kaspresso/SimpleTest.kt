package com.asemlab.samples.unittesting.kaspresso

import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.asemlab.samples.unittesting.R
import com.asemlab.samples.unittesting.ui.MainActivity
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import io.github.kakaocup.kakao.text.KButton
import io.github.kakaocup.kakao.text.KTextView
import org.junit.Rule
import org.junit.Test

class SimpleTest : TestCase() {

    @get:Rule
    val mainActivity = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun checkButton() {

        val button = KButton {
            withId(R.id.button)
        }

        button {
            isDisplayed()
        }

    }


    @Test
    fun changeTitle_onClick() {

        val button = KButton {
            withId(R.id.button)
        }

        val titleTV = KTextView {
            withId(R.id.titleTV)
        }

        titleTV {
            hasText(R.string.greeting)
        }


        button.click()

        titleTV {
            hasText(R.string.new_title)
        }

    }
}