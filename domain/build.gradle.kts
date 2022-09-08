import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    kotlin("kapt")
    kotlin("plugin.jpa")
    id("kotlin-allopen")
}

allOpen {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.MappedSuperclass")
    annotation("javax.persistence.Embeddable")
}

tasks {
    register<Exec>("syncSchema") {
        executable = "sh"
        commandLine = "${project.rootDir}/tools/sync-schema/sync_schema.sh".split(" ")
    }
}

dependencies {
    api("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    api("org.springframework.boot:spring-boot-actuator-autoconfigure:2.7.3")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    runtimeOnly("mysql:mysql-connector-java")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    kapt("org.springframework.boot:spring-boot-configuration-processor")
    api("com.querydsl:querydsl-jpa:5.0.0")
    kapt("com.querydsl:querydsl-apt:5.0.0:jpa")
}
repositories {
    mavenCentral()
}

tasks.withType<Jar> {
    enabled = true
}

tasks.withType<BootJar> {
    enabled = false
}
