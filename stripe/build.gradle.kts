import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import com.asemlab.samples.Configuration

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.hilt.plugin)

}
// TODO Add Your Stripe secret key and publishable key in local.properties file
val publishable_key: String = gradleLocalProperties(rootDir).getProperty("STRIPE_PUBLISHABLE_KEY")
val secret_key: String = gradleLocalProperties(rootDir).getProperty("SECRET_KEY")
val customer_id: String = gradleLocalProperties(rootDir).getProperty("CUSTOMER_ID")

android {
    namespace = "com.asemlab.samples.stripe"
    compileSdk = Configuration.compileSdk

    defaultConfig {
        minSdk = Configuration.minSdk
        targetSdk = Configuration.targetSdk
        versionCode = Configuration.versionCode
        versionName = Configuration.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        buildConfigField("String", "STRIPE_PUBLISHABLE_KEY", publishable_key)
        buildConfigField("String", "SECRET_KEY", secret_key)
        buildConfigField("String", "CUSTOMER_ID", customer_id)
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
        viewBinding = true
        buildConfig = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Stripe Android SDK
    implementation(libs.stripe.android)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.gson)
    implementation(platform(libs.okhttp.bom))
    implementation(libs.okhttp)
    implementation(libs.okhttp.interceptor)

    // Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
}