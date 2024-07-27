plugins {
    alias(libs.plugins.android.library)
}

android {
    namespace = "com.mati.tacker_data"
}

apply {
    from("$rootDir/base-module.gradle")
}

dependencies {
    "implementation"(project(Modules.core))
    "implementation"(project(Modules.trackerDomain))

    "implementation"(libs.retrofit)
    "implementation"(libs.converter.moshi)

    "implementation"(libs.logging.interceptor)

    "kapt"(libs.androidx.room.compiler)
    "implementation"(libs.androidx.room.ktx)
    "implementation"(libs.room.runtime)

}