import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
    id("org.sonarqube") version "7.0.1.6134"
    id("checkstyle")
    id("jacoco")
    id("application")
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("io.freefair.lombok") version "8.12.1"
}

application {
    mainClass.set("hexlet.code.App")
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.mashape.unirest:unirest-java:1.4.9")
    implementation("com.h2database:h2:2.2.224")
    implementation("com.zaxxer:HikariCP:5.1.0")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.17.0")
    implementation("org.apache.commons:commons-text:1.11.0")
    implementation("gg.jte:jte-runtime:3.1.16")
    implementation("gg.jte:jte:3.1.9")
    implementation("org.slf4j:slf4j-simple:2.0.9")
    implementation("io.javalin:javalin:6.4.0")
    implementation("io.javalin:javalin-bundle:6.1.3")
    implementation("io.javalin:javalin-rendering:6.1.3")
    implementation("org.jsoup:jsoup:1.18.3")
    implementation("org.postgresql:postgresql:42.7.2")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.17.1")
    testImplementation("org.assertj:assertj-core:3.27.2")
    testImplementation(platform("org.junit:junit-bom:5.10.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("com.squareup.okhttp3:mockwebserver:4.12.0")
}

tasks.test {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport)
    testLogging {
        exceptionFormat = TestExceptionFormat.FULL
        events = mutableSetOf(TestLogEvent.FAILED, TestLogEvent.PASSED, TestLogEvent.SKIPPED)
        // showStackTraces = true
        // showCauses = true
        showStandardStreams = true
    }
}

jacoco { toolVersion = "0.8.12" }

checkstyle {
    toolVersion = "10.26.1"
    config = resources.text.fromFile("config/checkstyle/checkstyle.xml")
}

sonar {
    properties {
        property("sonar.projectKey", "vvvilkha_java-project-72")
        property("sonar.organization", "vvvilkha")
        property("sonar.coverage.exclusions", "src/main/java/**/*")
    }
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)
    reports {
        xml.required.set(true)   // Sonar читает coverage из этого XML
        html.required.set(true)
        csv.required.set(false)
    }
}

configurations.all {
    exclude(group = "ch.qos.logback", module = "logback-classic")
    exclude(group = "org.slf4j", module = "slf4j-reload4j")
}