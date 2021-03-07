rootProject.name = "covid19"

pluginManagement {
    val springBootVersion: String by settings
    val springDependencyManagementVersion: String by settings
    val kotlinVersion: String by settings
    val sqldelightPluginVersion: String by settings
    val dgsPluginVersion: String by settings
    plugins {
        id("org.springframework.boot") version springBootVersion
        id("io.spring.dependency-management") version springDependencyManagementVersion
        kotlin("jvm") version kotlinVersion
        kotlin("plugin.spring") version kotlinVersion
        id("com.squareup.sqldelight") version sqldelightPluginVersion
        id("com.netflix.dgs.codegen") version dgsPluginVersion
    }
}
