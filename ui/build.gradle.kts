plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.sujith.catapidemo.ui"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
}

dependencies {
    implementation(project(":domain"))
    //Core
    implementation(libs.bundles.core)
    implementation(platform(libs.androidx.compose.bom))
    //Koin
    implementation(platform(libs.koin.bom))
    implementation(libs.bundles.koin)
    //Navigation
    implementation(libs.bundles.navigation)

    //Coil
    implementation(libs.coil.compose)
    //Lottie
    implementation(libs.lottie)
    //Unit test
    testImplementation(libs.bundles.unitTest)
    androidTestImplementation(libs.bundles.uiTest)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    debugImplementation(libs.bundles.debugImpl)
    implementation(libs.ui.tooling.preview)
}