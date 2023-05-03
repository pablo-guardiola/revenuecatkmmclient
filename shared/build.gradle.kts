plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    id("kotlin-parcelize")
    kotlin("plugin.serialization") version Versions.pluginKotlin
    `maven-publish`
    id("org.jetbrains.dokka") version Versions.pluginDokka
}
version = if (project.hasProperty("VERSION_NAME")) {
    project.property("VERSION_NAME") as String
} else {
    System.getenv("VERSION_NAME")
}
group = "com.pguardiola"
val iosFrameworkName = "RevenueCatKMMClient"

kotlin {
    android {
        publishAllLibraryVariants()
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    ios()
    // Note: iosSimulatorArm64 target requires that all dependencies have M1 support
    iosSimulatorArm64()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(Dependencies.ktorClientCore)

                implementation(Dependencies.kotlinSerializationJson)
                implementation(Dependencies.ktorClientContentNegotiation)
                implementation(Dependencies.ktorClientAuth)
                implementation(Dependencies.ktorSerialization)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(Dependencies.ktorClientAndroid)
            }
        }
        val androidUnitTest by getting {
            dependencies {
                implementation(kotlin(Dependencies.kotlinTestJUnit))
                implementation(Dependencies.jUnit)
            }
        }
        val iosMain by getting {
            dependencies {
                implementation(Dependencies.ktorClientIos)
            }
        }
        val iosTest by getting
        val iosSimulatorArm64Main by getting {
            dependsOn(iosMain)
        }
        val iosSimulatorArm64Test by getting {
            dependsOn(iosTest)
        }
    }

    cocoapods {
        name = iosFrameworkName
        summary = "RevenueCat HTTP KMM client."
        homepage = "https://github.com/pablo-guardiola/revenuecatkmmclient"
        ios.deploymentTarget = "13.0"
        framework {
            baseName = iosFrameworkName
        }

        podfile = project.file("../iosApp/Podfile")
    }
}

android {
    namespace = "com.pguardiola.revenuecatkmmclient"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
    }
}

val docsJar by project.tasks.registering(Jar::class) {
    group = "build"
    description = "Assembles KDoc / Dokka jar file for publishing"
    archiveClassifier.set("javadoc")
    from(rootProject.extra["kdocPath"] as File)
}

configure<PublishingExtension> {
    publications {
        register<MavenPublication>("RevenueCatShared") {
            groupId = group.toString()
            artifactId = property("pom.name").toString()
            version = version

            artifact("$buildDir/outputs/aar/shared-release.aar")

            pom {
                name.set(property("pom.name").toString())
                description.set(property("pom.description").toString())
                url.set(property("pom.url").toString())

                licenses {
                    license {
                        name.set(property("pom.license.name").toString())
                        url.set(property("pom.license.url").toString())
                    }
                }

                developers {
                    developer {
                        id.set(property("pom.developer.id").toString())
                        name.set(property("pom.developer.name").toString())
                    }
                }

                scm {
                    url.set(property("pom.smc.url").toString())
                }
            }

            artifact(tasks.getByName("sourcesJar"))
            artifact(docsJar.get())
        }
    }
}

tasks {
    withType<org.jetbrains.dokka.gradle.DokkaTask>().configureEach {
        outputDirectory.set(rootProject.extra["kdocPath"] as File)
        dokkaSourceSets.configureEach {
            reportUndocumented.set(true)

            perPackageOption {
                matchingRegex.set("com.pguardiola.revenuecatkmmclient.internal.*")
                suppress.set(true)
            }
        }
    }
    withType<org.jetbrains.kotlin.gradle.tasks.FatFrameworkTask>().configureEach {
        // Workaround for Kotlin 1.6.20 as this task keeps using `shared` for framework name
        baseName = iosFrameworkName
    }
}

tasks.named("publishRevenueCatSharedPublicationToMavenLocal") {
    dependsOn(":shared:bundleReleaseAar")
}
