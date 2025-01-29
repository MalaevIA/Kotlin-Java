plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.library"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.library"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.core)
    dependencies {
        // Основные библиотеки
        implementation(libs.appcompat)
        implementation(libs.material)
        implementation(libs.activity)
        implementation(libs.constraintlayout)

        // Зависимости для юнит-тестов
        testImplementation(libs.junit)
        testImplementation(libs.monitor)

        // Зависимости для инструментальных тестов
        androidTestImplementation(libs.ext.junit)
        androidTestImplementation(libs.espresso.core)
        androidTestImplementation("androidx.test.espresso:espresso-intents:3.4.0")
        androidTestImplementation("androidx.test:rules:1.4.0")

        // Убираем дублирующиеся строки
        // Зависимости для Room
        implementation("androidx.room:room-runtime:2.5.2")
        annotationProcessor("androidx.room:room-compiler:2.5.2")

        // ViewModel и LiveData
        implementation("androidx.lifecycle:lifecycle-viewmodel:2.6.0")
        implementation("androidx.lifecycle:lifecycle-livedata:2.6.0")

        // Обновление версии material
        implementation("com.google.android.material:material:1.9.0")
        testImplementation ("org.robolectric:robolectric:4.10.3")
        testImplementation ("junit:junit:4.13.2")

    }

}