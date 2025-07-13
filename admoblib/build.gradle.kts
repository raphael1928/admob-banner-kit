plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose")
    id("maven-publish")
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                from(components["release"])
                artifactId = "admoblib"
                groupId = "br.com.raphael.admoblib"
                version = "1.0.1"

                pom {
                    name.set("AdMobBannerKit")
                    description.set("A lightweight AdMob banner library for Android apps using Jetpack Compose or XML")
                }
            }
        }

        repositories {
            maven {
                name = "GitHubPackages"
                url = uri("https://maven.pkg.github.com/raphael1928/admob-banner-kit")
                credentials {
//                  Token com expiração de 7 dias somente para conseguir testar sem precisar pasar o gradle.properties
                    username = "raphael1928"
                    password = "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"
//                    username = project.findProperty("gpr.user") as String? ?: System.getenv("GPR_USER")
//                    password = project.findProperty("gpr.key") as String? ?: System.getenv("GPR_TOKEN")
                }
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
    testImplementation("io.mockk:mockk:1.13.10")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
}
