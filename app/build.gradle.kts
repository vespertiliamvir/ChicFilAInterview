plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.chicfilainterviewapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.chicfilainterviewapp"
        minSdk = 25
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Jetpack Compose
    implementation(libs.ui)
    implementation(libs.androidx.material)
    //noinspection GradleDependency
    implementation(libs.ui.tooling.preview)
    //noinspection GradleDependency
    debugImplementation(libs.ui.tooling)
    implementation(libs.androidx.activity.compose.v172)

    // ViewModel and LiveData for MVVM
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.lifecycle.runtime.ktx.v262)
    implementation(libs.androidx.lifecycle.livedata.ktx) // Optional, if using LiveData

    // Retrofit for networking
    implementation(libs.retrofit)
    implementation(libs.converter.gson) // Gson converter
    //Moshi:
    // Retrofit Scalars Converter
    implementation(libs.converter.scalars)

// Retrofit Moshi Converter (for JSON, if needed)
    implementation(libs.converter.moshi)

// Moshi (for JSON, if needed)
    implementation(libs.moshi)
    implementation(libs.moshi.kotlin)

    // Coroutines
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)

    // Coil for Image Loading
    implementation(libs.coil.compose)
    implementation(libs.coil.compose.v250)

    // Test dependencies
    testImplementation (libs.junit.jupiter.api)
    testImplementation (libs.junit.jupiter.engine)
    //noinspection UseTomlInstead
    testImplementation ("org.mockito:mockito-core:4.0.0")
    testImplementation (libs.mockito.inline)
    testImplementation (libs.mockito.kotlin)
    testImplementation (libs.mockito.android)
    testImplementation (libs.androidx.core.testing)
    testImplementation(libs.kotlinx.coroutines.test) // Use the appropriate version for your project
    testImplementation(libs.junit.jupiter.api)
    testImplementation(libs.junit.jupiter.engine)
    testImplementation(libs.kotlin.mockito.kotlin) // For mocking

    // If you haven't already included the JUnit dependency for tests
    testImplementation(libs.junit.jupiter.api)
    testImplementation(libs.junit.jupiter.engine)

}