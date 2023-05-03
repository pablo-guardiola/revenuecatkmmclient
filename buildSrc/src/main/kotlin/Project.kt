object Plugins {
    const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.pluginKotlin}"
    const val android = "com.android.tools.build:gradle:${Versions.pluginAndroidGradle}"
    const val ktlint = "org.jlleitschuh.gradle:ktlint-gradle:${Versions.pluginKtlint}"
    const val dokka = "org.jetbrains.dokka:dokka-gradle-plugin:${Versions.pluginDokka}"
    const val versions = "com.github.ben-manes:gradle-versions-plugin:${Versions.gradleVersions}"
}

object Versions {
    const val pluginKotlin = "1.8.20"
    const val pluginAndroidGradle = "8.0.0"
    const val pluginKtlint = "11.3.2"
    const val pluginDokka = "1.8.10"
    const val kotlinSerializationJson = "1.5.0"
    const val kotlinCoroutines = "1.6.4"
    const val ktor = "2.3.0"
    const val gradleVersions = "0.44.0"
    const val jUnit = "4.13.2"
}

object Dependencies {
    const val kotlinSerializationJson =
        "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.kotlinSerializationJson}"
    const val kotlinCoroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.kotlinCoroutines}"
    const val ktorClientCore = "io.ktor:ktor-client-core:${Versions.ktor}"
    const val ktorClientAndroid = "io.ktor:ktor-client-android:${Versions.ktor}"
    const val ktorClientIos = "io.ktor:ktor-client-ios:${Versions.ktor}"
    const val ktorClientContentNegotiation = "io.ktor:ktor-client-content-negotiation:${Versions.ktor}"
    const val ktorClientAuth = "io.ktor:ktor-client-auth:${Versions.ktor}"
    const val ktorSerialization = "io.ktor:ktor-serialization-kotlinx-json:${Versions.ktor}"
    const val jUnit = "junit:junit:${Versions.jUnit}"
    const val kotlinTestJUnit = "test-junit"
}
