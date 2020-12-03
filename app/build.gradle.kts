// 引用插件
plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
}

// Android属性
android {
    compileSdkVersion(AppConfig.compileSdkVersion)
    buildToolsVersion = (AppConfig.buildToolsVersion)

    defaultConfig {
        applicationId = AppConfig.applicationId
        minSdkVersion(AppConfig.minSdkVersion)
        targetSdkVersion(AppConfig.targetSdkVersion)
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName


        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
    }


    // 签名配置
    signingConfigs {
        register("release") {
            //别名
            keyAlias = "edwin"
            //别名密码
            keyPassword = "123456"
            //路径
            storeFile = file("src/main/jks/aivoice.jks")
            //密码
            storePassword = "123456"
        }
    }

    // 编译类型
    buildTypes {

        getByName("debug") {

        }

        getByName("release") {
            isMinifyEnabled = false

            // 自动签名打包
            signingConfig = signingConfigs.getByName("release")

            proguardFiles(
                getDefaultProguardFile("proguard-android.txt"),
                "proguard-rules.pro"
            )
        }

    }


    // 输出类型
    android.applicationVariants.all {
        // 编译类型
        val buildType = this.buildType.name
        outputs.all {

            // 输入APK
            if (this is com.android.build.gradle.internal.api.ApkVariantOutputImpl) {
                this.outputFileName = "AI_V${defaultConfig.versionName}_$buildType.apk"
            }
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
}

// 依赖
dependencies {

    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(DependenciesConfig.STD_LIB)
    implementation(DependenciesConfig.APP_COMPAT)
    implementation(DependenciesConfig.CONSTRAINT_LAYOUT)

    testImplementation("junit:junit:4.+")
    androidTestImplementation("androidx.test.ext:junit:1.1.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.2.0")

}