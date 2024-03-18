package com.asemlab.samples.unittesting.kaspresso

import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.asemlab.samples.unittesting.R
import com.asemlab.samples.unittesting.ui.MainActivity
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import io.github.kakaocup.kakao.text.KButton
import io.github.kakaocup.kakao.text.KTextView
import org.junit.Rule
import org.junit.Test

// TODO Use Kaspresso logging feature
class LogTest : TestCase() {

    @get:Rule
    val mainActivity = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun changeTitle_onClick() {

        run {
            val button = KButton {
                withId(R.id.button)
            }

            val titleTV = KTextView {
                withId(R.id.titleTV)
            }

            // TODO The step description will be printed in Logcat with "KASPRESSO" Tag
            step("Check initial title") {
                titleTV {
                    hasText(R.string.greeting)
                }
            }

            step("Click on button to change title") {
                button.click()
            }


            step("Check title after click") {

                // TODO Print on Logcat with "KASPRESSO_TEST" tag
                testLogger.d("The new title must be Title has changed")

                titleTV {
                    hasText(R.string.new_title)
                }
            }
        }

    }

}