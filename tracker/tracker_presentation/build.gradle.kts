plugins {
    alias(libs.plugins.android.library)
}

android {
    namespace = "com.mati.tracker_presentation"
}

apply {
    from("$rootDir/compose-module.gradle")
}

dependencies {
    "implementation"(project(Modules.core))
    "implementation"(project(Modules.coreUi))
    "implementation"(project(Modules.trackerDomain))
}