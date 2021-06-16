import io.github.diegoflassa.mobile_challenge_2021_android.buildsrc.Versions

// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
        jcenter() // Warning: this repository is going to shut down soon
    }
    dependencies {
        //classpath("com.android.tools.build:gradle:${Versions.gradle}")
        classpath("com.android.tools.build:gradle:7.1.0-alpha02")
        //classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinCompilerVersion}")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.31")

        //classpath("androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.safeargs_plugin}")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.4.0-alpha02")

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle.kts files
    }
}

plugins {
    id("com.diffplug.spotless") version "5.12.5" apply true
}

subprojects {
    apply(plugin = "com.diffplug.spotless")
    spotless {
        kotlin {
            target("**/*.kt")
            targetExclude("$buildDir/**/*.kt")
            targetExclude("bin/**/*.kt")

            ktlint(Versions.ktlint).userData(mapOf("disabled_rules" to "no-wildcard-imports"))
        }
    }
}