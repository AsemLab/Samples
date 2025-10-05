import com.asemlab.samples.Configuration
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.kotlin)
}

android {
    namespace = "${Configuration.baseNamespace}.base"
    compileSdk = Configuration.compileSdk

    // TODO Change outputs names for by BuildType, ex: 1.0.0-2024-07-24-debug, 1.0.0(1)-release
    applicationVariants.all {
        outputs.map { it as com.android.build.gradle.internal.api.ApkVariantOutputImpl }
            .all { output ->
                output.outputFileName = when (output.baseName) {
                    "release" -> "${Configuration.versionName}(${Configuration.versionCode})-${output.baseName}.apk"
                    else -> {
                        val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                        val currentTime = format.format(Date())
                        "${Configuration.versionName}-$currentTime-${output.baseName}.apk"
                    }
                }

                false
            }
    }

    defaultConfig {
        minSdk = Configuration.minSdk
        targetSdk = Configuration.targetSdk
        versionCode = Configuration.versionCode
        versionName = Configuration.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        // TODO Set general name for outputs, for .aab files
        setProperty(
            "archivesBaseName",
            "${Configuration.versionName}(${Configuration.versionCode})"
        )

        // TODO Include language resources in the APK for these specified languages (Optional)
        resourceConfigurations += mutableSetOf("en", "ar", "de")
    }

    buildTypes {
        getByName("release") {
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
    // TODO Generate per-app language support automatically, (NOT RECOMMENDED)
//    androidResources {
//        generateLocaleConfig = true
//    }
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
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(libs.epoxy)
    implementation(libs.epoxy.databinding)
    kapt(libs.epoxy.processor)
    implementation(libs.androidx.biometric)

    // TODO Add DataStore dependency
    implementation(libs.datastore)
}