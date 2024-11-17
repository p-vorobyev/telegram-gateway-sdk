plugins {
    alias { libs.plugins.kotlin.jvm }
    alias { libs.plugins.maven.publish }
}

group = "dev.voroby"
version = "1.0.0"

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    implementation(libs.coroutines.core)
    implementation(libs.arrow.core)
    implementation(libs.jackson.module)
    testImplementation(kotlin("test"))
    testImplementation(libs.bundles.kotest)
}

tasks.register("libraryVersion") {
    doLast { println(version) }
}

tasks.test {
    useJUnitPlatform()
}

tasks.jar {
    from(
        configurations.runtimeClasspath.get()
            .map { if (it.isDirectory) it else zipTree(it) }
    )
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    withSourcesJar()
}

kotlin {
    jvmToolchain(21)
}

publishing {
    publications {
        create<MavenPublication>("telegramGatewaySdkLibrary") {
            from(components["java"])
        }
    }

    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/p-vorobyev/*")
            credentials {
                username =  "p-vorobyev"
                password = System.getenv("GITHUB_TOKEN")
            }
        }
    }
}