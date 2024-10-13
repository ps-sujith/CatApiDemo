plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    kotlin("kapt")
}

android {
    namespace = "com.sujith.catapidemo.data"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

        buildConfigField("String", "BASE_URL", "\"https://api.thecatapi.com/\"")
        buildConfigField("String", "API_KEY", "\"live_LjtyFsPHWVJuXA5YOqr5QyTKsw1MfSAVdDgW52uTl3Hj0mXobNsrbFzB2VQ2KNWX\"")
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
    buildFeatures {
        buildConfig = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

}

dependencies {
    implementation(project(":domain"))
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    //Retrofit
    implementation(libs.bundles.retrofit)
    //Koin
    implementation(platform(libs.koin.bom))
    implementation(libs.bundles.koin)
    //Room
    implementation(libs.bundles.room)
    kapt (libs.androidx.room.compiler)
    //Unit test
    testImplementation(libs.bundles.unitTest)
    androidTestImplementation(libs.bundles.uiTest)
    androidTestImplementation(platform(libs.androidx.compose.bom))
}