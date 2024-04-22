/*
 * This file was generated by the Gradle 'init' task.
 *
 * This generated file contains a sample Java application project to get you started.
 * For more details on building Java & JVM projects, please refer to https://docs.gradle.org/8.7/userguide/building_java_projects.html in the Gradle documentation.
 */

plugins {
    // Apply the application plugin to add support for building a CLI application in Java.
    application
        id("org.openjfx.javafxplugin") version "0.1.0"

}

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
}

dependencies {
    // Use JUnit Jupiter for testing.
    testImplementation(libs.junit.jupiter)


    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    // This dependency is used by the application.
    implementation(libs.guava)



        implementation("org.openjfx:javafx-swing:17") // Add JavaFX Swing dependency
        implementation("org.openjfx:javafx-controls:17") // Add JavaFX controls dependency
        implementation("org.openjfx:javafx-fxml:17") // Add JavaFX FXML dependency
        // Add other JavaFX dependencies if needed
}

// Apply a specific Java toolchain to ease working on different environments.
java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

javafx {
    version = "21.0.2"
    modules("javafx.controls", "javafx.fxml", "javafx.swing")
}

application {
    // Define the main class for the application.
    mainClass = "ch.zhaw.it.pm2.jvmjourney.JVMJourney"
}

tasks.named<Test>("test") {
    // Use JUnit Platform for unit tests.
    useJUnitPlatform()
}
