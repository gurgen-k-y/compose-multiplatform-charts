@file:OptIn(org.jetbrains.kotlin.gradle.ExperimentalWasmDsl::class)

import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    id("io.github.gurgenky.kmp-conventions")
    alias(libs.plugins.android.kmp.library)
    alias(libs.plugins.compose)
    alias(libs.plugins.compose.compiler)
}

kotlin {
    android {
        namespace = "io.github.gurgenky.charts.example.shared"
        compileSdk = libs.versions.android.compileSdk.get().toInt()
        minSdk = libs.versions.android.minSdk.get().toInt()
        compilerOptions.jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17)
    }
    iosArm64()
    iosSimulatorArm64()
    jvm("desktop")
    wasmJs {
        browser()
        binaries.executable()
    }

    sourceSets {
        commonMain.dependencies {
            implementation(project(":charts"))
            implementation("org.jetbrains.compose.runtime:runtime:1.12.0")
            implementation("org.jetbrains.compose.foundation:foundation:1.12.0")
            implementation("org.jetbrains.compose.material:material:1.12.0")
            implementation("org.jetbrains.compose.ui:ui:1.12.0")
            implementation("org.jetbrains.compose.components:components-resources:1.12.0")
            implementation("org.jetbrains.compose.ui:ui-tooling-preview:1.12.0")
        }
        androidMain.dependencies {
            implementation(libs.androidx.activity.compose)
            implementation("org.jetbrains.compose.ui:ui-tooling-preview:1.12.0")
        }
        named("desktopMain") {
            dependencies {
            implementation(compose.desktop.currentOs)
            }
        }
    }
}

compose.desktop {
    application {
        mainClass = "io.github.gurgenky.charts.example.MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "io.github.gurgenky.charts.example"
            packageVersion = "1.0.0"
        }
    }
}
