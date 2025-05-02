plugins {
    kotlin("jvm") version "2.1.20"
    application
    id("io.github.jwharm.flatpak-gradle-generator") version "1.4.0"
}

application {
    mainClass = "main.MainKt"
    applicationDefaultJvmArgs += "--enable-native-access=ALL-UNNAMED"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("build/repository")
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("io.github.jwharm.javagi:gtk:0.12.0")
    implementation("io.github.jwharm.javagi:adw:0.12.0")
}

tasks.flatpakGradleGenerator {
    outputFile = file("$rootDir/flatpak/maven-dependencies.json")
    downloadDirectory = "build/repository"
}

tasks.installDist {
    destinationDir = file("/app/HelloApp")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(23)
}