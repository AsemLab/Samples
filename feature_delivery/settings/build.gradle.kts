import com.asemlab.samples.Configuration

plugins {
    // TODO Add dynamic feature plugin
    alias(libs.plugins.android.dynamic.feature)
    alias(libs.plugins.kotlin)
}
android {
//    namespace = "com.asemlab.samples.feature_delivery.settings"
    namespace = "com.asemlab.quakes.settings" // Used in order to upload with internal testing
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":feature_delivery:base"))
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(libs.androidx.navigation.fragment.ktx)

}