pluginManagement {
    repositories {
        mavenLocal() // 优先拉取本地 Maven 仓库（关键！）


        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenLocal() // 优先拉取本地 Maven 仓库（关键！）

        google()
        mavenCentral()
    }
}

rootProject.name = "ads2_sdk_hub"
include(":app")
include(":ads2_sdk_hub")
include(":interweb")
