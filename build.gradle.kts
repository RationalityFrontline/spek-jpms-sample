import org.jetbrains.kotlin.gradle.plugin.getKotlinPluginVersion

plugins {
	kotlin("jvm") version "1.4.10"
    `java-library`
    id("org.javamodularity.moduleplugin") version "1.7.0"
}

repositories {
    jcenter()
}

dependencies {
    val spekVersion = "2.0.14"
    testImplementation("org.jetbrains.kotlin:kotlin-test:${getKotlinPluginVersion()}")
    testImplementation("org.spekframework.spek2:spek-dsl-jvm:$spekVersion")
    testRuntimeOnly("org.spekframework.spek2:spek-runner-junit5:$spekVersion")
}

sourceSets.main {
    java.srcDirs("src/main/kotlin")
}

tasks.test {
    useJUnitPlatform {
        doFirst {
            classpath.forEach { it.mkdirs() }
        }
        jvmArgs = listOf(
            "--add-exports", "org.junit.platform.commons/org.junit.platform.commons.util=ALL-UNNAMED",
            "--add-exports", "org.junit.platform.commons/org.junit.platform.commons.logging=ALL-UNNAMED",
            "--add-reads", "sample.calculator=spek.dsl.jvm",
            "--add-reads", "sample.calculator=kotlin.test"
        )
        includeEngines("spek2")
    }
}