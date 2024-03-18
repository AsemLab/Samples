package com.asemlab.samples.unittesting.kaspresso

import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.asemlab.samples.unittesting.R
import com.asemlab.samples.unittesting.ui.MainActivity
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import io.github.kakaocup.kakao.text.KButton
import io.github.kakaocup.kakao.text.KTextView
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

// TODO Inherit TestCase to access device object
class DeviceObjectTest : TestCase() {

    @get:Rule
    val mainActivity = ActivityScenarioRule(MainActivity::class.java)


    // TODO Grant permission automatically
//    @get:Rule
//    val grantPermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
//        android.Manifest.permission.CALL_PHONE
//    )

    @Test
    fun toggleWifi() {

        // TODO Setup before run test
        before {

            // TODO Switch wifi on
            device.network.toggleWiFi(true)

        }.after {

            // TODO Tear down after run test

            device.network.toggleWiFi(true)
        }.run {

            //TODO Run test

            device.network.toggleWiFi(false)
        }


    }


    @Test
    fun pressHome() {
        device.exploit.pressHome()
    }

    @Test
    fun checkCurrentActivity() {
        device.activities.isCurrent(MainActivity::class.java)
    }

    @Test
    fun askForPermission_allow() {

        KButton {
            withId(R.id.permissionButton)
        }.click()

        Assert.assertTrue(device.permissions.isDialogVisible())
        device.permissions.allowViaDialog()

        KTextView {
            withId(R.id.titleTV)
        }.hasText(R.string.permission_allow)
    }

    @Test
    fun askForPermission_deny() {
        KButton {
            withId(R.id.permissionButton)
        }.click()

        Assert.assertTrue(device.permissions.isDialogVisible())
        device.permissions.denyViaDialog()

        KTextView {
            withId(R.id.titleTV)
        }.hasText(R.string.permission_deny)
    }


}