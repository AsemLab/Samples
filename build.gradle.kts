// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin) apply false
    alias(libs.plugins.kotlin.kapt) apply false
    alias(libs.plugins.safe.args) apply false
    alias(libs.plugins.hilt.plugin) apply false
    alias(libs.plugins.kotlin.parcelize) apply false
    // TODO Add Kotlin Serialization plugin  if don't use Gson serialization
//    alias(libs.plugins.kotlin.serialization) apply false

    //    TODO Add Realm plugin
    alias(libs.plugins.realm.plugin) apply false
    //    TODO Add the dependency for the Google services
    alias(libs.plugins.google.services) apply false
    alias(libs.plugins.androidTest) apply false
    //    TODO Add dynamic feature plugin
    alias(libs.plugins.android.dynamic.feature) apply false

}
