plugins {
    alias(libs.plugins.androidApplication)
    // id("com.android.application")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.inhouseproject.counselcolloquy"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.inhouseproject.counselcolloquy"
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
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.firebase.database)
    implementation(libs.firebase.storage)
    implementation(libs.firebase.auth)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    implementation(platform("com.google.firebase:firebase-bom:32.8.1"))
    implementation("com.google.firebase:firebase-analytics")
    implementation("com.google.firebase:firebase-firestore-ktx:24.11.1")
    implementation("com.google.firebase:firebase-database:20.3.1")
    implementation("com.google.firebase:firebase-storage:20.3.0")
    implementation("com.android.support:cardview-v7:28.0.0")
    implementation("com.squareup.picasso:picasso:2.71828")






}