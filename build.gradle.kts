plugins {
    kotlin("jvm") version "2.1.20"
    application
}

application {
    mainClass = "main.MainKt"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("io.github.jwharm.javagi:gtk:0.12.0")
    implementation("io.github.jwharm.javagi:adw:0.12.0")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(22)
}