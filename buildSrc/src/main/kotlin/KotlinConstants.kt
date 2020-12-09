/**
 * kotlin 常量
 */

object KotlinConstants {

    const val kotlinVersion = "1.3.72"
    const val gradleVersion = "4.1.1"
}

object AppConfig {

    // 依赖版本
    const val compileSdkVersion = 30

    // 编译工具版本
    const val buildToolsVersion = "30.0.2"

    // 包名
    const val applicationId = "com.edwin.aivoiceapp"

    // 最小SDK版本
    const val minSdkVersion = 21

    //  当前SDK版本
    const val targetSdkVersion = 30

    //  版本编码
    const val versionCode = 1

    //  版本名称
    const val versionName = "1.0"

}


object DependenciesConfig {
    //  kotlin 基础库
    const val STD_LIB = "org.jetbrains.kotlin:kotlin-stdlib:${KotlinConstants.kotlinVersion}"

    //  Android标准库
    const val APP_COMPAT = "androidx.appcompat:appcompat:1.1.0"

    //  kotlin核心库
    const val KTX_CORE = "androidx.core:core-ktx:1.2.0"

    const val CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:1.1.3"

    const val EVENT_BUS = "org.greenrobot:eventbus:3.2.0"

    //    ARouter
    const val AROUTER = "com.alibaba:arouter-api:1.5.1"
    const val AROUTER_COMPILER = "com.alibaba:arouter-compiler:1.5.1"

    //  RecyclerView
    const val RECYCLER_VIEW = "androidx.recyclerview:recyclerview:1.2.0-alpha01"

    //  Permissions
    const val AND_PERMISSIONS = "com.yanzhenjie:permission:2.0.2"

}

object ModuleConfig {
    // 是否APP

    val isApp = false

    //  包名
    const val MODULE_APP_MANAGER = "com.edwin.module_app_manager"
    const val MODULE_CONSTELLATION = "com.edwin.module_constellation"
    const val MODULE_DEVELOPER = "com.edwin.module_developer"
    const val MODULE_JOKE = "com.edwin.module_joke"
    const val MODULE_MAP = "com.edwin.module_map"
    const val MODULE_SETTING = "com.edwin.module_setting"
    const val MODULE_VOICE_SETTING = "com.edwin.module_voice_setting"
    const val MODULE_WEATHER = "com.edwin.module_weather"
}



