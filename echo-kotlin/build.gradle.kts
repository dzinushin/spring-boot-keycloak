plugins {
	id("org.springframework.boot") version "3.4.2"
	id("io.spring.dependency-management") version "1.1.7"
	kotlin("jvm") version "2.1.10"
	kotlin("plugin.spring") version "2.1.10"
}

group = "live.fin"

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("io.github.oshai:kotlin-logging-jvm:5.1.0")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks {
	withType<Test> {
		useJUnitPlatform()
	}

	getByName<Jar>("jar") {
		enabled = false
	}
}

kotlin {
	jvmToolchain(21)
}