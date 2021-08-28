package io.github.diegoflassa.mobile_challenge_2021_android.buildsrc

import java.io.File
import java.io.FileInputStream
import java.util.*

@Suppress("Unused", "MemberVisibilityCanBePrivate")
object Config {
    const val ktlint = "0.39.0"
    const val applicationId = "io.github.diegoflassa.mobile_challenge_2021_android"
    private const val versionMajor = 1
    private const val versionMinor = 0
    private const val versionPatch = 1
    private var versionClassifier = "debug"
    const val minimumSdkVersion = 26
    const val compileSdkVersion = 30
    const val compileSdkPreviewVersion = "S"
    const val targetSdkVersion = 30
    const val targetSdkPreviewVersion = "S"
    const val buildToolsVersion = "31.0.0"
    const val versionCode = 1//buildVersionCode()
    val versionName = buildVersionName()

    private fun buildVersionCode(): Int {
        return minimumSdkVersion * 10000000 + versionMajor * 10000 + versionMinor * 100 + versionPatch
    }

    private fun buildVersionNameWithoutClassifier(): String {
        return "mobile_challenge_2021_android"
    }

    @Suppress("Unused")
    fun buildVersionName(): String {
        val versionProps = Properties()
        val versionPropsFile = File("version.properties")
        val fileInputStream = FileInputStream(versionPropsFile)
        fileInputStream.use { fis ->
            if (versionPropsFile.exists()) {
                versionProps.load(fis)
            }
            val code = (versionProps["VERSION_CODE"] ?: "0").toString().toInt() + 1
            versionProps["VERSION_CODE"] = code.toString()
            versionProps.store(versionPropsFile.writer(), null)

            var versionName = buildVersionNameWithoutClassifier()
            if (versionClassifier.isNotEmpty()) {
                //versionName = versionName + "-" + versionClassifier + "-Build:${code}"
            } else {
                versionName += "-Build:${code}"
            }
        }
        return versionName
    }
}
