plugins {
    id ("com.android.application")
    id ("org.jetbrains.kotlin.android")
    id ("kotlin-parcelize")
}

android {
    namespace = "com.hanzeel.iptvandroidstudio"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.hanzeel.iptvandroidstudio"
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
}

dependencies {
    val media3_version = "1.4.1" // Usa la versión más reciente disponible
    implementation("androidx.media3:media3-exoplayer:$media3_version")       // Para reproducir audio/video
    implementation("androidx.media3:media3-exoplayer-hls:$media3_version")
    implementation("androidx.media3:media3-ui:$media3_version")               // UI de ExoPlayer
    implementation("androidx.media3:media3-session:$media3_version")          // Sesión de medios (para integración con notificaciones y controles externos)
    implementation ("androidx.activity:activity-ktx:1.5.1")
    implementation ("org.videolan.android:libvlc-all:3.3.13")


    // Glide
    implementation("com.github.bumptech.glide:glide:4.15.1") // Glide más reciente

    // Gson
    implementation("com.google.code.gson:gson:2.8.8")

    // Dependencias de AndroidX y Material
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    // Dependencias para pruebas
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
