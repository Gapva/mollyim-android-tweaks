/*
 * Copyright 2023 Signal Messenger, LLC
 * SPDX-License-Identifier: AGPL-3.0-only
 */

val signalJavaVersion: JavaVersion by rootProject.extra
val signalKotlinJvmTarget: String by rootProject.extra

plugins {
  id("java-library")
  id("org.jetbrains.kotlin.jvm")
  id("com.squareup.wire")
}

java {
  sourceCompatibility = signalJavaVersion
  targetCompatibility = signalJavaVersion
}

kotlin {
  jvmToolchain {
    languageVersion = JavaLanguageVersion.of(signalKotlinJvmTarget)
  }
}

wire {
  kotlin {
    javaInterop = true
  }

  sourcePath {
    srcDir("src/main/protowire")
  }
}

dependencies {
  implementation(libs.kotlin.reflect)
  implementation(libs.kotlinx.coroutines.core)
  implementation(libs.kotlinx.coroutines.core.jvm)
  implementation(libs.google.libphonenumber)
  implementation(libs.rxjava3.rxjava)
  implementation(libs.rxjava3.rxkotlin)

  testImplementation(testLibs.junit.junit)
  testImplementation(testLibs.assertk)
  testImplementation(testLibs.kotlinx.coroutines.test)
}
