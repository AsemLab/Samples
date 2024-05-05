import com.asemlab.samples.Configuration
import com.android.build.gradle.internal.tasks.factory.dependsOn
import io.gitlab.arturbosch.detekt.Detekt
import org.jlleitschuh.gradle.ktlint.reporter.ReporterType

@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin)
    // TODO Add Ktlint plugin
    alias(libs.plugins.ktlint)
    // TODO Add Detekt plugin
    alias(libs.plugins.detekt)
}

android {
    namespace = "com.asemlab.samples.lint"
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

    // TODO Add Detekt format dependency for using with autoCorrect
    detektPlugins(libs.detekt.formatting)

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

val runKtlint = false

if (runKtlint) {
    // TODO Apply Ktlint check and format before Build the project
    tasks.preBuild.dependsOn("ktlintCheck")
    tasks.preBuild.dependsOn("ktlintFormat")
} else {
    tasks.preBuild.dependsOn("detekt")

}

// TODO Change Klint reports folder
tasks.withType<org.jlleitschuh.gradle.ktlint.tasks.GenerateReportsTask> {
    reportsOutputDirectory.set(
        project.layout.projectDirectory.dir("Ktlint reports/$name"),
    )
}

detekt {
    allRules = true
    // TODO Format(fix) issues if applicable
    autoCorrect = true
    // TODO when set to false, build will fail when there is issues
    ignoreFailures = true
    ignoredVariants = listOf("release")
    source.setFrom("src/main/", "src/androidTest/", "src/test/") // Change default sources

    // TODO Add more configuration, located in main module folder
    config.setFrom("/config.yml")

}

tasks.withType<Detekt>().configureEach {
    reports {
        html.required.set(true)
        txt.required.set(true)
        sarif.required.set(true)
        md.required.set(true)

        xml.required.set(true)
        xml.outputLocation.set(File("build/detekt/detekt.xml"))
    }

    // TODO Exclude utils package from Detekt
    exclude("**/utils/**")

}
