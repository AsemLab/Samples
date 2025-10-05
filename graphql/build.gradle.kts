import com.asemlab.samples.Configuration

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.hilt.plugin)
    // TODO Add Apollo plugin
    alias(libs.plugins.apollo)
}

apollo {
    service("service") {
        packageName.set("${Configuration.baseNamespace}.qraphql")
        // TODO Install Apollo plugin from Android Studio Plugins
        // TODO Add to download schema with Apollo plugin
        introspection {
            endpointUrl.set("https://countries.trevorblades.com/graphql")
            schemaFile.set(file("src/main/graphql/schema.graphqls"))
        }

    }
}


android {
    namespace = "${Configuration.baseNamespace}.graphql"
    compileSdk = Configuration.compileSdk

    defaultConfig {
        applicationId = "${Configuration.baseNamespace}.graphql"
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
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(libs.androidx.viewmodel)
    implementation(libs.androidx.activity)

    // Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    // TODO Add Apollo dependency
    implementation(libs.apollo.runtime)
}