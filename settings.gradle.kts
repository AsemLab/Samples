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
        maven {
            setUrl("https://jitpack.io")
        }
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
include(":chuncker")
include(":unit_testing")
include(":benchmark")
include(":websocket")
include(":stripe")
include(":broadcast_receiver")
include(":build_variants")
include(":feature:home")
include(":feature:hotel")
include(":graphql")
include(":app_distribution")
include(":media_player")
