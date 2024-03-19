package com.asemlab.samples.unittesting.kaspresso

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.rule.GrantPermissionRule
import com.asemlab.samples.unittesting.R
import com.asemlab.samples.unittesting.ui.MainActivity
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import io.github.kakaocup.kakao.text.KButton
import org.junit.Rule
import org.junit.Test

class ScreenshotTest: TestCase() {

    // TODO Mandatory in order to save the screenshots and must add in manifest.xml
    @get:Rule
    val permissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        android.Manifest.permission.READ_EXTERNAL_STORAGE,
        android.Manifest.permission.WRITE_EXTERNAL_STORAGE
    )

    @get:Rule
    val mainActivity = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun checkButton() {

        val button = KButton {
            withId(R.id.button)
        }

        // TODO Take screenshot during tests and save it to device
        // TODO Screenshots are saved in Documents\screenshots\{package name}\{test name}
        device.screenshots.take("Before click")

        button {
            isDisplayed()
            click()
        }

        device.screenshots.take("After click")
    }
}