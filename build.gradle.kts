plugins {
    id("java")
    kotlin("jvm") version "1.9.25"
}

group = "com.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform("org.springframework:spring-framework-bom:6.1.14"))
    implementation("org.springframework:spring-context")
    implementation("org.slf4j:slf4j-simple:2.0.16")
    implementation("org.springframework:spring-jdbc")

    compileOnly("org.projectlombok:lombok:1.18.34")
    annotationProcessor("org.projectlombok:lombok:1.18.34")

    implementation("org.postgresql:postgresql:42.7.3")
    implementation("com.zaxxer:HikariCP:5.1.0")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}
