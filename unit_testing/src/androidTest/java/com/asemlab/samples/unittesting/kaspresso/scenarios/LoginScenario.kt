package com.asemlab.samples.unittesting.kaspresso.scenarios

import com.asemlab.samples.unittesting.kaspresso.screens.LoginScreen
import com.kaspersky.kaspresso.testcases.api.scenario.Scenario
import com.kaspersky.kaspresso.testcases.core.testcontext.TestContext

// TODO Scenario allows to combine several steps into one
class LoginScenario(private val username: String, private val password: String) : Scenario() {


    override val steps: TestContext<Unit>.() -> Unit = {

        step("Enter username and password") {
            LoginScreen {
                usernameET {
                    typeText(username)
                }

                passwordET {
                    typeText(password)
                }
            }
        }

        step("Click on loginButton") {
            LoginScreen {
                loginButton {
                    click()
                }
            }
        }
    }

}