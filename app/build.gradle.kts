plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.daggerHilt)
    id("androidx.navigation.safeargs.kotlin")
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.kabe.genshincharacters"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.kabe.genshincharacters"
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

    buildFeatures {
        buildConfig = true
        viewBinding = true
    }

    kotlinOptions {
        jvmTarget = "1.8"
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

    // Navigation
    implementation (libs.navigation.fragment)
    implementation (libs.navigation.ui)

    // Lifecycle / ktx
    implementation (libs.lifecycle.livedata)
    implementation (libs.lifecycle.viewmodel)
    implementation (libs.lifecycle.runtime)
    implementation (libs.activity.ktx)
    implementation (libs.fragment.ktx)

    // Dagger Hilt
    implementation(libs.dagger.hilt.android)
    ksp (libs.dagger.hilt.android.compiler)

    // Retrofit
    implementation (libs.squareup.retrofit2.retrofit)
    implementation (libs.squareup.retrofit2.converter.gson)
    implementation (libs.squareup.okhttp3)
    implementation (libs.squareup.gson)

    // Room
    implementation (libs.androidx.room)
    implementation (libs.androidx.room.ktx)
    ksp (libs.androidx.room.compiler)
    annotationProcessor (libs.androidx.room.compiler)

}
