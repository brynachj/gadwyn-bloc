import com.bmuschko.gradle.docker.tasks.image.*

group = "com.brynachj"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    id("application")
    id("com.bmuschko.docker-remote-api")
    kotlin("jvm")
    kotlin("plugin.spring")
}

val syncWebAppArchive by tasks.creating(Sync::class) {
    dependsOn("assemble")
    from("build/libs/application-0.0.1-SNAPSHOT.jar")
    into("build/docker")
}

val createDockerfile by tasks.creating(Dockerfile::class) {
    dependsOn("syncWebAppArchive")
    from("openjdk:8-jre-alpine")
    label(mapOf("maintainer" to "Brynach Jones 'brynachj@gmail.com'"))
    copyFile("./application-0.0.1-SNAPSHOT.jar", "/app/application.jar") // TODO: make src a reference
    entryPoint("java")
    defaultCommand("-jar", "/app/application.jar")
    exposePort(8080)
    runCommand("apk --update --no-cache add curl")
    instruction("HEALTHCHECK CMD curl -f http://localhost:8080/health || exit 1")
}

val buildDockerImage by tasks.creating(DockerBuildImage::class) {
    dependsOn(createDockerfile)
    images.add("brynachj/gadwyn-bloc:latest")
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.assertj:assertj-core:3.14.0")
}

tasks.startScripts {
    mainClassName = "com.brynachj.gadwynbloc.GadwynBlocApplication"
}