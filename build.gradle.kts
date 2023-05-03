plugins {
    // trick: for the same plugin versions in all sub-modules
    id("com.android.application").version(Versions.pluginAndroidGradle).apply(false)
    id("com.android.library").version(Versions.pluginAndroidGradle).apply(false)
    kotlin("android").version(Versions.pluginKotlin).apply(false)
    kotlin("multiplatform").version(Versions.pluginKotlin).apply(false)
    id("org.jlleitschuh.gradle.ktlint").version(Versions.pluginKtlint).apply(false)
    id("org.jetbrains.dokka").version(Versions.pluginDokka).apply(false)
}

buildscript {

    extra.apply {
        val kdocDirName = "kdoc"
        set("kdocPath", File(buildDir.path, kdocDirName))
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }

    apply(plugin = "org.jlleitschuh.gradle.ktlint")
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
