import com.asemlab.samples.Configuration

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin)
}

android {
    namespace = "${Configuration.baseNamespace}.build_variants"
    compileSdk = Configuration.compileSdk

    defaultConfig {
        applicationId = "com.asemlab.samples.build_variants"
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
    flavorDimensions += listOf("api", "mode")
    productFlavors {
        create("demo") {
            dimension = "mode"
        }

        create("full") {
            dimension = "mode"
        }


        create("minApi24") {
            dimension = "api"
            minSdk = 24

            versionCode = 30000 + (android.defaultConfig.versionCode ?: 0)
//            versionNameSuffix = "-minApi24"
        }

        create("minApi23") {
            dimension = "api"
            minSdk = 23
            versionCode = 20000 + (android.defaultConfig.versionCode ?: 0)
//            versionNameSuffix = "-minApi23"
        }

        create("minApi21") {
            dimension = "api"
            minSdk = 21
            versionCode = 10000 + (android.defaultConfig.versionCode ?: 0)
//            versionNameSuffix = "-minApi21"
        }
    }

    androidComponents {
        beforeVariants { variantBuilder ->
            // To check for a certain build type, use variantBuilder.buildType == "<buildType>"
            // Disable build variants: minApi21DemoDebug & minApi21DemoRelease
            if (variantBuilder.productFlavors.containsAll(
                    listOf(
                        "api" to "minApi21", "mode" to "demo"
                    )
                )
            ) {
                // Gradle ignores any variants that satisfy the conditions above.
                variantBuilder.enable = false
            }
        }
    }

    // Change product flavor source folders
    sourceSets.getByName("minApi21") {
        java.setSrcDirs(listOf("other/java"))
        res.setSrcDirs(listOf("other/res"))
        manifest.srcFile("other/AndroidManifest.xml")
    }

    sourceSets.getByName("minApi24") {
        java.setSrcDirs(listOf("minApi24/java"))
        res.setSrcDirs(listOf("minApi24/res"))
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

    "minApi24Implementation"("com.github.AsemLab:toaster:0.4.0")
    "demoImplementation"("com.github.AsemLab:toaster:0.4.0")

}