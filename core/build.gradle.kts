plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.mati.core"
}

apply {
    from("$rootDir/base-module.gradle")
}

dependencies {

}