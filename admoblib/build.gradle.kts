plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose")
    id("maven-publish")
}

group = "br.com.raphael.admoblib"
version = "1.0.0"

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                from(components["release"])
                groupId = group as String
                artifactId = "admoblib"
                version = version as String
            }
        }
    }
}

android {
    namespace = "br.com.raphael.admoblib"
    compileSdk = 34

    defaultConfig {
        minSdk = 26
    }

    publishing {
        singleVariant("release") {
            withSourcesJar()
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.12"
    }
}

dependencies {
    // AdMob
    implementation("com.google.android.gms:play-services-ads:24.4.0")

    // Compose
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.runtime:runtime-android:1.8.3")
    implementation("androidx.compose.ui:ui-android:1.8.3")
    // Compose UI
    implementation("androidx.compose.ui:ui:1.8.3")
    implementation("androidx.compose.ui:ui-tooling-preview:1.8.3")
    implementation("androidx.compose.ui:ui-tooling:1.8.3")

    // Material 3
    implementation("androidx.compose.material3:material3:1.3.2")

    // Compose Runtime
    implementation("androidx.compose.runtime:runtime:1.8.3")
    implementation("androidx.compose.runtime:runtime-livedata:1.8.3")
    

    // Testes
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
}
