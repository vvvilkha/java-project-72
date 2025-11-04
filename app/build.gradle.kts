plugins {
    id("java")
    id("org.sonarqube") version "7.0.1.6134"
    id("checkstyle")
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}

checkstyle {
    toolVersion = "10.26.1"
    config = resources.text.fromFile("config/checkstyle/checkstyle.xml")
}

sonar {
    properties {
        property("sonar.projectKey", "vvvilkha_java-project-72")
        property("sonar.organization", "vvvilkha")
    }
}