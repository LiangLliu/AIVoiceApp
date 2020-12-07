plugins {
    if (ModuleConfig.isApp) {
        id("com.android.application")
    } else {
        id("com.android.library")
    }
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
}

android {
    compileSdkVersion(AppConfig.compileSdkVersion)
    buildToolsVersion = (AppConfig.buildToolsVersion)

    defaultConfig {

        if (ModuleConfig.isApp) {
            applicationId = ModuleConfig.MODULE_SETTING
        }

        minSdkVersion(AppConfig.minSdkVersion)
        targetSdkVersion(AppConfig.targetSdkVersion)
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName

        //ARouter
        kapt {
            arguments {
                arg("AROUTER_MODULE_NAME", project.name)
            }
        }

        testInstrumentationRunner("androidx.test.runner.AndroidJUnitRunner")
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false

            proguardFiles(
                getDefaultProguardFile("proguard-android.txt"),
                "proguard-rules.pro"
            )
        }
    }
    // 依赖操作

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    // 动态替换资源

    sourceSets {
        getByName("main") {
            if (ModuleConfig.isApp) {
                manifest.srcFile("src/main/manifest/AndroidManifest.xml")
            } else {
                manifest.srcFile("src/main/AndroidManifest.xml")
            }
        }
    }
}

dependencies {

    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(project(":lib_base"))
    kapt(DependenciesConfig.AROUTER_COMPILER)

}