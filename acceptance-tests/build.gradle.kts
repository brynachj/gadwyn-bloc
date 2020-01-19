group = "com.brynachj"
version = "0.0.1-SNAPSHOT"

plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    kotlin("jvm")
    kotlin("plugin.spring")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.assertj:assertj-core:3.14.0")
    testImplementation("io.rest-assured:rest-assured:3.3.0")
}

//dependencies {
////    implementation "org.jetbrains.kotlin:kotlin-stdlib-jdk8"
//    implementation("org.jetbrains.kotlin:kotlin-reflect")
//    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
//    testImplementation("org.springframework.boot:spring-boot-starter-test")
//    testImplementation("org.assertj:assertj-core:3.14.0")
//    testImplementation("commons-logging:commons-logging")
//    testImplementation("org.hamcrest:hamcrest")
////    testImplementation("com.google.guava:guava")
//    testImplementation("io.rest-assured:rest-assured:3.3.0")
////    testImplementation("org.codehaus.groovy:groovy-all")
//    testImplementation("org.assertj:assertj-core")
//
//}