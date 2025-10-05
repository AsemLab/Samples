import com.asemlab.samples.Configuration

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin)
    // TODO Add the Google services Gradle plugin
    alias(libs.plugins.google.services)
}

android {
    namespace = "${Configuration.baseNamespace}.appdistribution"
    compileSdk = Configuration.compileSdk

    defaultConfig {
        applicationId = "com.asemlab.samples.appdistribution"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    // TODO Add Firebase dependencies
    implementation(libs.firebase.appdistribution.api.ktx)
    debugImplementation(libs.firebase.appdistribution)
}