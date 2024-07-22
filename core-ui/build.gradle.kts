plugins {
    alias(libs.plugins.android.library)
}

android {
    namespace = "com.mati.coreUi"
}

apply {
    from("$rootDir/compose-module.gradle")
}

dependencies {

}