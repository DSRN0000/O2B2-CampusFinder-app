// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
}

buildscript {
    dependencies {
        // AGP 버전을 8.2.0으로 설정합니다.
        classpath("com.android.tools.build:gradle:8.2.0")
    }
}