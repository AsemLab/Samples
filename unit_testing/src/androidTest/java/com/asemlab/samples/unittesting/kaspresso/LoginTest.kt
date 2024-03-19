package com.asemlab.samples.unittesting.kaspresso

import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.asemlab.samples.unittesting.kaspresso.scenarios.LoginScenario
import com.asemlab.samples.unittesting.kaspresso.screens.UserScreen
import com.asemlab.samples.unittesting.ui.LoginActivity
import com.asemlab.samples.unittesting.ui.UserActivity
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test

class LoginTest : TestCase() {

    @get:Rule
    val loginActivity = ActivityScenarioRule(LoginActivity::class.java)

    @Test
    fun login_correctCredentials_authorized() {

        run {

            scenario(LoginScenario(LoginActivity.correctUsername, LoginActivity.correctPassword))

            device.activities.isCurrent(UserActivity::class.java)

            UserScreen {
                userTV {
                    isDisplayed()
                    hasText(LoginActivity.correctUsername)
                }
            }

        }

    }

    @Test
    fun login_wrongPassword_unauthorized() {

        run {

            scenario(LoginScenario(LoginActivity.correctUsername, "1234"))

            device.activities.isCurrent(LoginActivity::class.java)

        }

    }

    @Test
    fun login_wrongUsername_unauthorized() {

        run {

            scenario(LoginScenario("BLA BLA", LoginActivity.correctPassword))

            device.activities.isCurrent(LoginActivity::class.java)

        }

    }

}