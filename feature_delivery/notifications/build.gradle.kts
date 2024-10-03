import com.asemlab.samples.Configuration

plugins {
    alias(libs.plugins.android.dynamic.feature)
    alias(libs.plugins.kotlin)
}
android {
//    namespace = "com.asemlab.samples.feature_delivery.gallery"
    namespace = "com.asemlab.quakes.gallery"
    compileSdk = Configuration.compileSdk

    defaultConfig {
        minSdk = Configuration.minSdk
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

dependencies {
    implementation(project(":feature_delivery:base"))
    implementation(libs.androidx.core.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}