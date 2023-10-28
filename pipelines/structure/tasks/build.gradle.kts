plugins {
    kotlin("jvm") version "1.9.0"
    application
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "snailtown"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.8.20")
    // Kotlin Spark API
    implementation("org.jetbrains.kotlinx.spark:kotlin-spark-api_3.3.2_2.13:1.2.4")
    // Apache Spark
    compileOnly("org.apache.spark:spark-sql_2.13:3.3.2")
}

kotlin {
    jvmToolchain(8)
}

application {
    mainClass.set("MainKt")
}

tasks.shadowJar {
    setProperty("zip64", true)

    // other shadow jar config
}