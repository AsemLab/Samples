import com.android.build.gradle.internal.tasks.factory.dependsOn
import org.jlleitschuh.gradle.ktlint.reporter.ReporterType

@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin)
    // TODO Add Ktlint plugin
    alias(libs.plugins.ktlint)
}

android {
    namespace = "com.asemlab.samples.lint"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.asemlab.samples.lint"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
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
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}

// TODO Ktlint config
// TODO Also add .editorconfig to root project folder
ktlint {
    // TODO when set to false, build will fail when there is issues
    ignoreFailures.set(false)
    android.set(true)

    // TODO Filter the folders
    filter {
//        exclude("**/generated/**")
//        include("**/kotlin/**")
    }

    // TODO Set the Ktlint report styles
    reporters {
        reporter(ReporterType.HTML)
        reporter(ReporterType.PLAIN)
        reporter(ReporterType.JSON)
    }
}

// TODO Apply Ktlint check and format before Build the project
tasks.preBuild.dependsOn("ktlintCheck")
tasks.preBuild.dependsOn("ktlintFormat")

// TODO Change Klint reports folder
tasks.withType<org.jlleitschuh.gradle.ktlint.tasks.GenerateReportsTask> {
    reportsOutputDirectory.set(
        project.layout.projectDirectory.dir("Ktlint reports/$name"),
    )
}
