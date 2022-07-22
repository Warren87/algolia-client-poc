plugins {
    java
    kotlin("jvm") version "1.6.10"
    kotlin("plugin.serialization") version "1.6.21"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // for Gradle version 6.0 and higher
    implementation("com.algolia:algoliasearch-client-kotlin:2.0.0")

    // Choose one of the following HTTP clients
    // For JVM:
    implementation("io.ktor:ktor-client-apache:2.0.1")
    implementation("io.ktor:ktor-client-java:2.0.1")
    implementation("io.ktor:ktor-client-jetty:2.0.1")
    
    
    implementation(kotlin("stdlib"))
    testCompile("junit", "junit", "4.12")
}
