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
}

object ModuleConfig {
    // 是否APP

    val isApp = true
}



