import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions.useIR = true

plugins {
    id("org.springframework.boot") version "2.4.3"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.4.30"
    kotlin("plugin.spring") version "1.4.30"
    id("com.squareup.sqldelight") version "1.4.4"
    id("com.netflix.dgs.codegen") version "4.0.12"
    id("com.google.cloud.tools.jib") version "2.8.0"
}

group = "com.joshrotenberg"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    api("com.netflix.graphql.dgs:graphql-dgs-spring-boot-starter:3.1.1")

    implementation("com.graphql-java:graphql-java-extended-scalars:1.0")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.batch:spring-batch-core")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("com.squareup.sqldelight:sqlite-driver:1.4.4")
    implementation("org.xerial:sqlite-jdbc:3.34.0")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    developmentOnly("org.springframework.boot:spring-boot-devtools")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.batch:spring-batch-test")
}

@OptIn(kotlin.ExperimentalStdlibApi::class)
tasks.withType<com.netflix.graphql.dgs.codegen.gradle.GenerateJavaTask> {
    generateClient = true
    packageName = "com.example.demo.generated"
    typeMapping = mutableMapOf("Date" to "java.time.LocalDate")
}

sqldelight {
    database("Covid19") {
        packageName = "com.joshrotenberg.db"
        dialect = "sqlite:3.24"
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
