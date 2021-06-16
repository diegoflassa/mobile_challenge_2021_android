import io.github.diegoflassa.mobile_challenge_2021_android.buildsrc.Config
import io.github.diegoflassa.mobile_challenge_2021_android.buildsrc.Versions

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-parcelize")
    id("kotlin-kapt")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    compileSdk = Config.compileSdkVersion
    //compileSdkPreview = Config.compileSdkPreviewVersion
    buildToolsVersion = Config.buildToolsVersion

    defaultConfig {
        applicationId = Config.applicationId
        minSdk = Config.minimumSdkVersion
        targetSdk = Config.targetSdkVersion
        //targetSdkPreview = Config.targetSdkPreviewVersion
        versionCode = Config.versionCode
        versionName = Config.versionName
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    testOptions {
        emulatorSnapshots {
            // Generates snapshots that are compressed into a single TAR file.
            compressSnapshots = true
        }
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
    buildFeatures {
        viewBinding = true
    }
    lint {
        isAbortOnError = false
    }
}
kapt {
	correctErrorTypes = true 
} 
dependencies {
    // Kotlin + coroutines
    implementation("androidx.work:work-runtime-ktx:${Versions.workVersion}")
    // optional - RxJava2 support
    implementation("androidx.work:work-rxjava2:${Versions.workVersion}")
    // optional - GCMNetworkManager support
    implementation("androidx.work:work-gcm:${Versions.workVersion}")

    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    // Volley
    implementation("com.android.volley:volley:${Versions.volley}")

    // Coil COroutines Image Loader
    implementation("io.coil-kt:coil:${Versions.coil}")

    // Preferences DataStore
    implementation("androidx.datastore:datastore-preferences:${Versions.data_store}")

    // Proto DataStore
    implementation("androidx.datastore:datastore-core:${Versions.data_store}")

    //Retrofix 2
    implementation("com.squareup.retrofit2:retrofit:${Versions.retrofit}")
    implementation("com.squareup.retrofit2:adapter-rxjava3:${Versions.retrofit_adapter}")
    implementation("com.squareup.retrofit2:converter-moshi:${Versions.converter_moshi}")
    implementation("com.squareup.retrofit2:converter-gson:${Versions.converter}")

    // Moshi
    implementation("com.squareup.moshi:moshi-kotlin:1.12.0")
    kapt("com.squareup.moshi:moshi-kotlin-codegen:1.12.0")

    //GSON
    implementation("com.google.code.gson:gson:${Versions.gson}")

    // RX Java 3
    implementation("io.reactivex.rxjava3:rxjava:${Versions.rxjava}")
    implementation("io.reactivex.rxjava3:rxandroid:${Versions.rxandroid}")

    // Koin main features for Android (Scope,ViewModel ... )
    implementation("io.insert-koin:koin-core:${Versions.koin}")
    // Koin main features for Android (Scope,ViewModel ...)
    implementation("io.insert-koin:koin-android:${Versions.koin}")
    // Koin Android - experimental builder extensions
    implementation("io.insert-koin:koin-android-ext:${Versions.koin}")
    // Koin for Jetpack WorkManager
    implementation("io.insert-koin:koin-androidx-workmanager:${Versions.koin}")

    // Dagger & Hilt
    //implementation("com.google.dagger:hilt-android:${Versions.hilt}")
    //kapt("com.google.dagger:hilt-compiler:${Versions.hilt}")

    // Leak Canary
    debugImplementation("com.squareup.leakcanary:leakcanary-android:${Versions.leakcanary}")

    implementation("com.google.android.material:material:${Versions.material}")
    implementation("com.google.gms:google-services:${Versions.google_services}")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlinCompilerVersion}")
    implementation("androidx.activity:activity-ktx:${Versions.activity_ktx}")
    implementation("androidx.annotation:annotation:${Versions.annotation}")
    implementation("androidx.appcompat:appcompat:${Versions.appcompat}")
    implementation("androidx.browser:browser:${Versions.browser}")
    implementation("androidx.constraintlayout:constraintlayout:${Versions.constraintlayout}")
    implementation("androidx.core:core-ktx:${Versions.core_ktx}")
    implementation("androidx.fragment:fragment-ktx:${Versions.fragment_ktx}")
    implementation("androidx.legacy:legacy-support-v4:${Versions.legacy_support_v4}")
    implementation("androidx.legacy:legacy-support-core-utils:${Versions.legacy_support_core_utils}")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}")
    implementation("androidx.lifecycle:lifecycle-viewmodel-savedstate:${Versions.lifecycle}")
    implementation("androidx.lifecycle:lifecycle-common-java8:${Versions.lifecycle}")
    implementation("androidx.lifecycle:lifecycle-common:${Versions.lifecycle}")
    implementation("androidx.navigation:navigation-runtime-ktx:${Versions.navigation}")
    implementation("androidx.navigation:navigation-fragment-ktx:${Versions.navigation}")
    implementation("androidx.navigation:navigation-ui-ktx:${Versions.navigation}")
    implementation("androidx.preference:preference-ktx:${Versions.preference_ktx}")
    implementation("androidx.recyclerview:recyclerview:${Versions.recyclerview}")
    implementation("androidx.recyclerview:recyclerview-selection:${Versions.recyclerview_selection}")
    implementation("androidx.room:room-runtime:${Versions.room}")
    implementation("androidx.room:room-ktx:${Versions.room}")
    implementation("androidx.vectordrawable:vectordrawable-animated:${Versions.vectordrawable_animated}")

    // Annotation processor
    kapt("androidx.lifecycle:lifecycle-common-java8:${Versions.lifecycle}")
    kapt("androidx.databinding:databinding-compiler-common:${Versions.databinding}")
    kapt("androidx.room:room-compiler:${Versions.room}")

    // Testing dependencies
    androidTestImplementation("androidx.annotation:annotation:${Versions.annotation}")
    androidTestImplementation("androidx.test:core:${Versions.core}")
    androidTestImplementation("androidx.test.espresso:espresso-core:${Versions.espresso}")
    testImplementation("androidx.test.espresso:espresso-intents:${Versions.espresso}")
    testImplementation("androidx.test.ext:truth:${Versions.test}")
    androidTestImplementation("androidx.test.ext:junit-ktx:${Versions.junit_ktx}")
    androidTestImplementation("androidx.test:rules:${Versions.test}")
    androidTestImplementation("androidx.test:runner:${Versions.test}")

    androidTestImplementation("junit:junit:${Versions.junit}")
}