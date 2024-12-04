plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("androidx.navigation.safeargs.kotlin")
    id("kotlin-kapt")
}

android {
    namespace = "com.example.tastoria"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.tastoria"
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
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(libs.retrofit)

    // Gson converter for Retrofit
    implementation(libs.converter.gson)

    // Optional: For logging requests and responses
    implementation(libs.logging.interceptor)

    // ViewModel and LiveData (if not already included)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.livedata.ktx)

    implementation (libs.glide)
    annotationProcessor(libs.compiler)

    // Jetpack Compose integration
    implementation(libs.androidx.navigation.compose)

    // Views/Fragments integration
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.navigation.ui)

    // Feature module support for Fragments
    implementation(libs.androidx.navigation.dynamic.features.fragment)

    // Testing Navigation
    androidTestImplementation(libs.androidx.navigation.testing)

    implementation (libs.picasso)
    implementation(libs.androidx.core.splashscreen)


    implementation(libs.androidx.paging.runtime)

    implementation(libs.androidx.room.runtime)
    kapt(libs.androidx.room.compiler)

    // Room Database dependency
  //  implementation(libs.androidx.room.runtime.v250)

    // Room KTX dependency for coroutine support
    implementation (libs.androidx.room.ktx)

    // Room compiler (for annotation processing)
    //kapt(libs.androidx.room.compiler.v250)

    // Coroutine support library (if not already added)
    implementation (libs.kotlinx.coroutines.android)
}