import java.util.regex.Pattern.compile

plugins {
	id("java")
	kotlin("jvm") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25"
	kotlin("kapt") version "1.9.25"
	id("org.springframework.boot") version "3.5.11"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "com.zpatricklee"
version = "0.0.1-SNAPSHOT"
description = "Content Calendar REST API"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-validation")

	// Use kapt for Spring Boot configuration metadata
	kapt("org.springframework.boot:spring-boot-configuration-processor")

	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
	testRuntimeOnly("com.h2database:h2")
	implementation("org.postgresql:postgresql")
	implementation("org.jetbrains.kotlin:kotlin-reflect")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

// Ensure Gradle recognizes Java sources even in Kotlin-first projects
sourceSets {
	named("main") {
		java {
			setSrcDirs(listOf("src/main/java"))
		}
	}
}

// Copy kapt-generated metadata into the final class output
tasks.register<Copy>("syncConfigMetadata") {
	dependsOn("kaptKotlin")
	from("build/tmp/kapt3/classes/main/META-INF/spring-configuration-metadata.json")
	into("build/classes/java/main/META-INF")
}

// Ensure metadata is copied before classes are packaged
tasks.named("classes") {
	dependsOn("syncConfigMetadata")
}

// Prevent duplicate metadata entries in the final JAR
tasks.named<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
	duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}

tasks.named<Jar>("jar") {
	enabled = false
}