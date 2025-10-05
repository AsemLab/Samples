package com.asemlab.samples.base.utils

import android.app.LocaleConfig
import android.app.LocaleManager
import android.content.Context
import android.os.Build
import android.os.LocaleList
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import com.asemlab.samples.base.R

object LanguageHelper {

    // TODO Set per-app supported languages dynamically
    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    fun updateSupportedLocales(context: Context) {
        val localeManager = context
            .getSystemService(LocaleManager::class.java)

        // For getOverrideLocaleConfig
        val overrideLocaleConfig = localeManager.overrideLocaleConfig
        val supportedLocales = overrideLocaleConfig?.supportedLocales

        // For setOverrideLocaleConfig, update supported and remove old ones
        localeManager.overrideLocaleConfig = LocaleConfig(
            LocaleList.forLanguageTags("en-US,ar")
        )

    }


    // TODO Use AndroidX to change locale
    fun showPickerDialog(context: Context) {
        var selectedLocale = AppCompatDelegate.getApplicationLocales()[0]?.language ?: "en"
        val selectedItem =
            context.resources.getStringArray(R.array.language_codes).indexOf(selectedLocale)

        AlertDialog.Builder(context)
            .setTitle("Choose your language")
            .setSingleChoiceItems(R.array.languages, selectedItem) { _, which ->
                when (which) {
                    0 -> selectedLocale = "en"
                    1 -> selectedLocale = "ar"
                    2 -> selectedLocale = "de"
                }
            }
            .setNegativeButton("Close") { d, _ ->
                d.dismiss()
            }
            .setPositiveButton("Apply") { d, _ ->

                val appLocale: LocaleListCompat = LocaleListCompat.forLanguageTags(selectedLocale)
                // Call this on the main thread as it may require Activity.restart()
                AppCompatDelegate.setApplicationLocales(appLocale)

                d.dismiss()
            }
            .show()
    }
}