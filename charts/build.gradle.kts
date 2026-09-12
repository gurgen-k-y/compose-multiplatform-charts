@file:OptIn(org.jetbrains.kotlin.gradle.ExperimentalWasmDsl::class)

plugins {
    id("io.github.gurgenky.kmp-conventions")
    alias(libs.plugins.android.kmp.library)
    alias(libs.plugins.compose)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.dokka)
    alias(libs.plugins.maven.publish)
}

kotlin {
    android {
        namespace = "io.github.gurgenky.charts"
        compileSdk = libs.versions.android.compileSdk.get().toInt()
        minSdk = libs.versions.android.minSdk.get().toInt()
        compilerOptions.jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17)
        withHostTest {}
    }
    iosArm64()
    iosSimulatorArm64()
    jvm("desktop")
    wasmJs { browser() }

    sourceSets {
        commonMain.dependencies {
            implementation("org.jetbrains.compose.runtime:runtime:1.12.0")
            implementation("org.jetbrains.compose.ui:ui:1.12.0")
            implementation("org.jetbrains.compose.foundation:foundation:1.12.0")
            implementation("org.jetbrains.compose.material:material:1.12.0")
            implementation("org.jetbrains.compose.material:material-icons-extended:1.7.3")
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

mavenPublishing {
    publishToMavenCentral(automaticRelease = true)
    if (providers.gradleProperty("signingInMemoryKey").isPresent) {
        signAllPublications()
    }

    pom {
        name.set("Compose Multiplatform Charts")
        description.set("Customizable Canvas charts for Compose Multiplatform on Android, iOS, desktop, and Wasm.")
        inceptionYear.set("2022")
        url.set("https://github.com/gurgen-k-y/compose-multiplatform-charts")

        licenses {
            license {
                name.set("MIT License")
                url.set("https://opensource.org/license/mit")
                distribution.set("repo")
            }
        }
        developers {
            developer {
                id.set("gurgen-k-y")
                name.set("Gurgen Khachatryan")
                url.set("https://github.com/gurgen-k-y")
            }
        }
        scm {
            url.set("https://github.com/gurgen-k-y/compose-multiplatform-charts")
            connection.set("scm:git:git://github.com/gurgen-k-y/compose-multiplatform-charts.git")
            developerConnection.set("scm:git:ssh://git@github.com/gurgen-k-y/compose-multiplatform-charts.git")
        }
        issueManagement {
            system.set("GitHub Issues")
            url.set("https://github.com/gurgen-k-y/compose-multiplatform-charts/issues")
        }
    }
}
