import com.android.build.api.dsl.Packaging
import com.asemlab.samples.Configuration

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin)
    alias(libs.plugins.kotlin.kapt)

}

android {
    namespace = "com.asemlab.samples.unittesting"
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

    testOptions{
        unitTests {
            // TODO Enables unit tests to use Android resources, assets, and manifests.
            isIncludeAndroidResources = true
        }
    }
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }

    packaging.resources {
        pickFirsts += "/META-INF/LICENSE.md"
        pickFirsts += "/META-INF/LICENSE-notice.md"
        pickFirsts += "/META-INF/AL2.0"
        pickFirsts += "/META-INF/LGPL2.1"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.activity)
    testImplementation(libs.junit)
    testImplementation(libs.kotlinx.coroutines.test)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // TODO To be used with test(src)
    testImplementation(libs.androidx.espresso.core)

    // TODO Add Mockk dependency
    implementation(libs.mockk)

    // TODO Add Robolectric dependency
    testImplementation(libs.robolectric)

    // TODO Add Kaspresso dependency
    implementation(libs.kaspresso)
}