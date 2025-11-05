plugins {
    id("java")
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

    implementation("io.javalin:javalin:6.3.0")
    implementation("io.javalin:javalin-rendering:6.4.0")
    implementation("gg.jte:jte:3.1.16")
    implementation("org.jsoup:jsoup:1.18.3")

    implementation("com.konghq:unirest-java-bom:4.4.5")
    implementation("com.konghq:unirest-java-core:4.4.5")
    implementation("com.konghq:unirest-modules-gson:4.4.5")
    implementation("com.konghq:unirest-modules-jackson:4.4.5")

    implementation("com.zaxxer:HikariCP:5.1.0")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    runtimeOnly("org.slf4j:slf4j-simple:2.0.17")
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.assertj:assertj-core:3.25.3")
    testImplementation("com.squareup.okhttp3:mockwebserver:4.12.0")
    testImplementation("net.datafaker:datafaker:2.4.2")
    testImplementation(platform("org.junit:junit-bom:5.10.1"))

    runtimeOnly("com.h2database:h2:2.3.232")
    runtimeOnly("org.postgresql:postgresql:42.7.4")
}

tasks.test {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport)
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