plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.ksp)
}

android {
    namespace = "com.miguelmialdea.taskquest"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.miguelmialdea.taskquest"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        multiDexEnabled = true // Añadir esta línea

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    packaging {
        resources {
            excludes += listOf(
                "META-INF/native-image/org.mongodb/bson/native-image.properties",
                "META-INF/DEPENDENCIES",
                "META-INF/LICENSE",
                "META-INF/LICENSE.txt",
                "META-INF/license.txt",
                "META-INF/NOTICE",
                "META-INF/NOTICE.txt",
                "META-INF/notice.txt",
                "META-INF/ASL2.0",
                "META-INF/*.kotlin_module",
                "META-INF/native/**",
                "META-INF/native-image/**"
            )
        }
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get()
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17 // Actualizar a Java 17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17" // Actualizar a Java 17
    }

    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(libs.multidex)
    // Modules
    implementation(project(":domain"))
    implementation(project(":data"))

    // AndroidX Core
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity.compose)

    // Navigation
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.navigation.runtime.ktx)

    // Compose
    implementation(platform(libs.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Room
    implementation(libs.bundles.room)
    ksp(libs.room.compiler)

    // Koin
    implementation(libs.bundles.koin)

    // Coroutines
    implementation(libs.bundles.coroutines)

    // Lifecycle
    implementation(libs.bundles.lifecycle)

    // Testing
    testImplementation(libs.bundles.testing)
    androidTestImplementation(libs.bundles.testing)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)

    implementation(libs.multidex)
}