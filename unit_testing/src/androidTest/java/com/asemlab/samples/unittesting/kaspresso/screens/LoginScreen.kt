package com.asemlab.samples.unittesting.kaspresso.screens

import com.asemlab.samples.unittesting.R
import com.asemlab.samples.unittesting.ui.LoginActivity
import com.kaspersky.kaspresso.screens.KScreen
import io.github.kakaocup.kakao.edit.KEditText
import io.github.kakaocup.kakao.text.KButton

// TODO KScreen facilities dealing with views in the same layout
object LoginScreen : KScreen<LoginScreen>() {

    override val layoutId: Int = R.layout.activity_login
    override val viewClass: Class<*> = LoginActivity::class.java

    val loginButton = KButton { withId(R.id.loginButton) }
    val usernameET = KEditText { withId(R.id.usernameET) }
    val passwordET = KEditText { withId(R.id.passwordET) }

}