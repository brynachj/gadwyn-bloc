import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import com.bmuschko.gradle.docker.tasks.image.*

plugins {
    id("org.springframework.boot") version "2.2.3.RELEASE"
    id("io.spring.dependency-management") version "1.0.8.RELEASE"
	id("com.bmuschko.docker-remote-api") version "6.1.3"
    kotlin("jvm") version "1.3.61"
    kotlin("plugin.spring") version "1.3.61"
}

group = "com.brynachj"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
    mavenCentral()
}

docker {
	url.set("https://192.168.59.103:2376")
	certPath.set(File(System.getProperty("user.home"), ".boot2docker/certs/boot2docker-vm"))

	registryCredentials {
		url.set("https://index.docker.io/v1/")
		username.set("brynachj")
		password.set(System.getenv("DOCKER_PASSWORD"))
		email.set("brynachj@gmail.com")
	}
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
}

subprojects {
	apply {
		plugin("org.jetbrains.kotlin.jvm")
	}

	group = "project"
	version = "0.0.1-SNAPSHOT"

	repositories {
		mavenCentral()
	}

	val implementation by configurations

	dependencies {
		implementation(kotlin("stdlib-jdk8"))
	}

	tasks.withType<KotlinCompile> {
		kotlinOptions.jvmTarget = "1.8"
	}

	tasks.withType<Test> {
		useJUnitPlatform()
	}

	tasks.withType<KotlinCompile> {
		kotlinOptions {
			freeCompilerArgs = listOf("-Xjsr305=strict")
			jvmTarget = "1.8"
		}
	}
}

fun getConfigurationProperty(envVar: String, sysProp: String): String {
	return if (!System.getenv(envVar).isNullOrEmpty()) {
		System.getenv(envVar)
	} else {
		project.findProperty(sysProp).toString()
	}
}
