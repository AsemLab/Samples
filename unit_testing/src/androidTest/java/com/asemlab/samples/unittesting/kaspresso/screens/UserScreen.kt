package com.asemlab.samples.unittesting.kaspresso.screens

import com.asemlab.samples.unittesting.R
import com.asemlab.samples.unittesting.ui.UserActivity
import com.kaspersky.kaspresso.screens.KScreen
import io.github.kakaocup.kakao.text.KButton

object UserScreen : KScreen<UserScreen>() {

    override val layoutId: Int = R.layout.activity_user
    override val viewClass: Class<*> = UserActivity::class.java

    val userTV = KButton { withId(R.id.userTV) }

}