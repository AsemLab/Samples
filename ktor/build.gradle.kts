import com.asemlab.samples.Configuration

@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin)
    alias(libs.plugins.kotlin.kapt)
    // TODO Add Kotlin Serialization plugin if don't use Gson serialization
//    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.asemlab.samples.ktor"
    compileSdk = Configuration.compileSdk

    defaultConfig {
        minSdk = Configuration.minSdk
        targetSdk = Configuration.targetSdk
        versionCode = Configuration.versionCode
        versionName = Configuration.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        dataBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.viewmodel)
    implementation(libs.androidx.activity)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(libs.koin)
    implementation(libs.glide)
    kapt(libs.glide.compiler)

    // TODO Add Kotlin Serialization library if don't use Gson serialization
//    implementation(libs.kotlinx.serialization.json)

//    TODO Add Ktor dependencies
    implementation(libs.ktor.core)
    implementation(libs.ktor.android)
    // TODO Add json library if don't use Gson serialization
    implementation(libs.ktor.serialization)
    implementation(libs.ktor.serialization.gson)
    implementation(libs.ktor.negotiation)
    implementation(libs.ktor.logging)
    implementation(libs.logback.classic)

}