import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.3.0"
    id("io.spring.dependency-management") version "1.1.5"
    kotlin("jvm") version "1.9.24"
    kotlin("plugin.spring") version "1.9.24"
} // 나중에 추가할 수 있음

group = "com.teamsparta"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web") // <- 이게 있어여 restapi와 꺼지지 않는다. 확인하기
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    //swagger
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.2.0")
    //데이터 베이스를 사용하려면 jpa, postgresql
    //jpa
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    //postgresql 실행시간에 이용할 수 있어야 함.
    runtimeOnly("org.postgresql:postgresql")
} // 집중해서 할 부분

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
