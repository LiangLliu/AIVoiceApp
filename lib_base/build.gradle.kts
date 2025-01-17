plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("android.extensions")
}

android {
    compileSdkVersion(AppConfig.compileSdkVersion)
    buildToolsVersion = (AppConfig.buildToolsVersion)

    defaultConfig {

        minSdkVersion(AppConfig.minSdkVersion)
        targetSdkVersion(AppConfig.targetSdkVersion)
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName

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
}

dependencies {

    api(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    api(DependenciesConfig.STD_LIB)
    api(DependenciesConfig.APP_COMPAT)
    api(DependenciesConfig.CONSTRAINT_LAYOUT)

    // EventBUS
    api(DependenciesConfig.EVENT_BUS)

    //    ARouter
    api(DependenciesConfig.AROUTER)

    //    recyclerview
    api(DependenciesConfig.RECYCLER_VIEW)

    //   permissions
    api(DependenciesConfig.AND_PERMISSIONS)

    // ViewPager
    api(DependenciesConfig.VIEW_PAGER)
    api(DependenciesConfig.MATERIAL)

    //  Lottie
    api(DependenciesConfig.LOTTIE)

    //刷新
    api(DependenciesConfig.REFRESH_KERNEL)
    api(DependenciesConfig.REFRESH_HEADER)
    api(DependenciesConfig.REFRESH_FOOT)

    //图表
    api(DependenciesConfig.CHART)

    //引入Jar与AAR
    api(files("libs/BaiduLBS_Android.jar"))
    api(files("libs/IndoorscapeAlbumPlugin.jar"))


    api(project(":lib_network"))
    api(project(":lib_voice"))

}