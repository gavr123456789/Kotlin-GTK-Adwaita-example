plugins {
    kotlin("jvm") version "2.4.0"
    application
    id("io.github.jwharm.flatpak-gradle-generator") version "1.8.0"
}



application {
    mainClass = "main.MainKt"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("build/repository")
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("org.java-gi:gtk:1.0.0-RC2")
    implementation("org.java-gi:adw:1.0.0-RC2")

}



tasks.named<JavaExec>("run") {
    val javaGiJvmArgs = buildList {
        val nativeLibraryPath = listOf("/opt/homebrew/lib", "/usr/local/lib")
            .filter { file(it).isDirectory }
            .joinToString(File.pathSeparator)

        add("--enable-native-access=ALL-UNNAMED")

        if (System.getProperty("os.name").contains("Mac", ignoreCase = true)) {
            add("-XstartOnFirstThread")
            if (nativeLibraryPath.isNotBlank()) {
                add("-Djavagi.path=$nativeLibraryPath")
            }
        }
    }
    jvmArgs(*javaGiJvmArgs.toTypedArray())
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

//kotlin {
//    jvmToolchain(23)
//}