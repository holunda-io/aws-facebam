import io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  kotlin("jvm") version Versions.kotlin
  kotlin("plugin.spring") version Versions.kotlin
  id("org.springframework.boot") version Versions.springBoot
}
apply {
  plugin("io.spring.dependency-management")
}

configure<DependencyManagementExtension> {
  imports {
    mavenBom("org.apache.camel:camel-parent:${Versions.camel}")
    mavenBom("io.zeebe:zb-bom:${Versions.zeebe}")
  }
}

dependencies {
  compile(project(":cloud:worker:facebam-worker-common"))
  compile("io.zeebe.camel:camel-zeebe-api:${Versions.camelZeebe}")
  compile(kotlin("stdlib-jdk8"))
  compile(kotlin("reflect"))
  compile("org.springframework.boot:spring-boot-starter")
  compile("org.apache.camel:camel-spring-boot-starter")

  compile("org.apache.camel:camel-jackson")
  compile("com.fasterxml.jackson.module:jackson-module-kotlin")


  compile("org.imgscalr:imgscalr-lib:4.2")

  compile("io.github.microutils:kotlin-logging:1.6.10")
}

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
  jvmTarget = Versions.java
  freeCompilerArgs = listOf("-Xjsr305=strict")
}
val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
  jvmTarget = Versions.java
  freeCompilerArgs = listOf("-Xjsr305=strict")
}
