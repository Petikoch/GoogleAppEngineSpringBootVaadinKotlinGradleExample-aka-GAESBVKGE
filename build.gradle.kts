import com.google.cloud.tools.gradle.appengine.appyaml.AppEngineAppYamlExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

//https://github.com/GoogleCloudPlatform/app-gradle-plugin/issues/300
buildscript {
	repositories {
		jcenter()
		mavenCentral()
	}
	dependencies {
		classpath("com.google.cloud.tools:appengine-gradle-plugin:2.+")
	}
}

//https://github.com/GoogleCloudPlatform/app-gradle-plugin
apply(plugin = "com.google.cloud.tools.appengine")

plugins {
	id("org.springframework.boot") version "2.1.5.RELEASE"
	id("io.spring.dependency-management") version "1.0.7.RELEASE"
	kotlin("jvm") version "1.3.31"
	kotlin("plugin.spring") version "1.3.31"
	id("com.github.ben-manes.versions") version "0.21.0"
}

group = "ch.petikoch.examples"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
	mavenCentral()
	jcenter()
}

extra["vaadinVersion"] = "13.0.7"

dependencies {
	implementation("com.vaadin:vaadin-spring-boot-starter")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

dependencyManagement {
	imports {
		mavenBom("com.vaadin:vaadin-bom:${property("vaadinVersion")}")
	}
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}

the<AppEngineAppYamlExtension>().apply {
	// for details see https://github.com/GoogleCloudPlatform/app-gradle-plugin/blob/master/USER_GUIDE.md
	deploy {
		version = "GCLOUD_CONFIG" // points to gcloud default project
		projectId = "GCLOUD_CONFIG" // gcloud generates the version for you
		stopPreviousVersion = true  // default - stop the current version
		promote = true              // default - & make this the current version
	}
}
