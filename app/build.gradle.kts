plugins {
    alias(libs.plugins.androidApplication)
}

android {
    namespace = "com.example.taskmanagerjava"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.taskmanagerjava"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    configurations { implementation.get().exclude(mapOf("group" to "org.jetbrains", "module" to "annotations")) }


}

dependencies {
    implementation("com.google.code.gson:gson:2.11.0")
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.room.compiler)
    testImplementation(libs.junit)
    androidTestImplementation(libs.espresso.core)
}