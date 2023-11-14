import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}

group = "tb.sampleapps"
version = "0.1.1-SNAPSHOT"

repositories {
    google()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    mavenLocal()
}

kotlin {
    jvm {
        jvmToolchain(11)
        withJava()
    }
    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs)
                // implementation("org.jetbrains.compose.runtime:runtime-jvm:0.4.0")
                implementation("androidx.compose.runtime:runtime:1.2.1")
                implementation("androidx.compose.foundation:foundation:1.2.1")
                // For Compose Material 3 you will need to use the appropriate dependency, but as of my last update, Material 3 is not directly available for desktop.
                implementation("org.jetbrains.compose.material:material:1.2.1")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.0")
                implementation("org.lightningdevkit:ldk-node-jvm:0.1.0")
            }
        }
        val jvmTest by getting
    }
}

compose.desktop {
    application {
        mainClass = "tb.sampleapps.pistolwallet.MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "pistol-wallet"
            packageVersion = "1.0.0"
        }
    }
}
