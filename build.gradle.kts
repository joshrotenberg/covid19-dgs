import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions.useIR = true

val dgsSpringBootStarterVersion: String by project
val graphqlJavaExtendedScalarsVersion: String by project
val sqldelightSQLiteDriverVersion: String by project
val sqliteJdbcVersion: String by project

plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    kotlin("jvm")
    kotlin("plugin.spring")
    id("com.squareup.sqldelight")
    id("com.netflix.dgs.codegen")
}

group = "com.joshrotenberg"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    api("com.netflix.graphql.dgs:graphql-dgs-spring-boot-starter:$dgsSpringBootStarterVersion")

    implementation("com.graphql-java:graphql-java-extended-scalars:$graphqlJavaExtendedScalarsVersion")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.batch:spring-batch-core")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    implementation("com.squareup.sqldelight:sqlite-driver:$sqldelightSQLiteDriverVersion")
    implementation("org.xerial:sqlite-jdbc:$sqliteJdbcVersion")
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
