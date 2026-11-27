// settings.gradle.kts — версия с зеркалом Maven Central (Google-зеркало для Google Maven).
// Зачем: раннеры GitHub Actions иногда получают HTTP 429 Too Many Requests от repo.maven.apache.org
// (общий IP-пул + агрессивный rate-limit). Зеркало maven-central.storage-download.googleapis.com
// отдаёт те же артефакты, но с другого хоста, поэтому 429 не прилетает.
//
// Как применить: в своём форке открыть settings.gradle.kts → карандаш → заменить всё содержимое на этот файл
// → Commit changes → Actions → Build debug APK → Run workflow.

pluginManagement {
  repositories {
    // Зеркала — ПЕРВЫМИ, чтобы Gradle не ходил на заблокированный хост
    maven("https://maven-central.storage-download.googleapis.com/maven2/") {
      name = "GoogleMavenCentralMirror"
    }
    google {
      content {
        includeGroupByRegex("com\\.android.*")
        includeGroupByRegex("com\\.google.*")
        includeGroupByRegex("androidx.*")
      }
    }
    // Официальные адреса — запасной вариант
    mavenCentral()
    gradlePluginPortal()
  }
}

dependencyResolutionManagement {
  repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
  repositories {
    maven("https://maven-central.storage-download.googleapis.com/maven2/") {
      name = "GoogleMavenCentralMirror"
    }
    google()
    mavenCentral()
  }
}

rootProject.name = "Shannon Modem Tweaks"
include(":app")
