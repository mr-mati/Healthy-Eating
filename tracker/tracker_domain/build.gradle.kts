plugins {
    alias(libs.plugins.android.library)
}

android {
    namespace = "com.mati.tacker_domain"
}

apply {
    from("$rootDir/base-module.gradle")
}

dependencies {
    "implementation"(project(Modules.core))
    "implementation"(libs.kotlinx.coroutines.core)
}


