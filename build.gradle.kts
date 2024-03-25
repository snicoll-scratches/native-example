
import net.nemerosa.versioning.VersioningExtension
import org.gradle.plugins.ide.idea.model.IdeaModel
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.dsl.SpringBootExtension
import org.springframework.boot.gradle.tasks.aot.ProcessAot
import org.springframework.boot.gradle.tasks.run.BootRun

plugins {

    id("idea")
    id("org.springframework.boot") version "3.2.2"
    id("io.spring.dependency-management") version "1.1.4"
    id("com.google.cloud.tools.jib") version "3.4.0"
    id("net.nemerosa.versioning") version "2.8.2"
    id("org.graalvm.buildtools.native") version "0.10.1"

    kotlin("jvm") version "1.9.22"
    kotlin("plugin.spring") version "1.9.22"

}

repositories {
    gradlePluginPortal()
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-cache")
    implementation("com.github.ben-manes.caffeine:caffeine")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")

    implementation("io.github.openfeign:feign-core:13.1")
    implementation("io.github.openfeign:feign-jackson:13.1")
    implementation("io.github.openfeign:feign-httpclient:13.1")

    implementation("org.slf4j:jcl-over-slf4j")

    testImplementation(kotlin("test"))
    testImplementation("io.mockk:mockk:1.13.9")
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(module = "mockito-core")
    }

    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
}

configure<SpringBootExtension> {
    buildInfo()
}

configure<IdeaModel> {
    module {
        inheritOutputDirs = true
    }
}

kotlin {
    jvmToolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
        vendor.set(JvmVendorSpec.ADOPTIUM)
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<JavaCompile> {
    sourceCompatibility = JavaVersion.VERSION_21.toString()
    targetCompatibility = JavaVersion.VERSION_21.toString()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = JavaVersion.VERSION_21.toString()
    }
}

configure<VersioningExtension> {
    /**
     * Add GitHub CI branch name environment variable
     */
    branchEnv = listOf("GITHUB_REF_NAME")
}

tasks.withType<BootRun> {
    jvmArgs(
        arrayOf(
            "-Dspring.config.additional-location=optional:file:/config/application.yaml"
        )
    )
}

tasks.withType<ProcessAot> {
    args("-Dspring.config.additional-location=optional:file:/config/application.yaml")
}