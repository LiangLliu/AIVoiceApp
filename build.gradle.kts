// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {

    val kotlin_version = "1.4.20"
    val gradle_version = "4.1.1"

    repositories {
        google()
        jcenter()
    }
    dependencies {


        classpath("com.android.tools.build:gradle:$gradle_version")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version")

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

tasks {
    val clean by registering(Delete::class) {
        delete(buildDir)
    }
}

