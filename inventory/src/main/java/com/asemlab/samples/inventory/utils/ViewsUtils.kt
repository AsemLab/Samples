package com.asemlab.samples.inventory.utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.google.android.material.snackbar.Snackbar

fun showSnackbarShortly(v: View, message: String, actionTitle: String, action: () -> Unit) {

    Snackbar.make(v.context, v, message, Snackbar.LENGTH_SHORT)
        .setAction(actionTitle) {
            action()
        }.show()

}

fun hideKeyboard(context: Context, view: View) {
    val inputManager: InputMethodManager =
        context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputManager.hideSoftInputFromWindow(
        view.getWindowToken(),
        InputMethodManager.HIDE_NOT_ALWAYS
    )
}