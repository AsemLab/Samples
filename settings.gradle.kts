pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "Samples"
include(":app")
include(":appshortcuts")
include(":navigation-component")
include(":viewpager2")
include(":koin")
include(":ktor")
include(":realm")
include(":firestore")
include(":lint")
include(":timber")
include(":leak")
