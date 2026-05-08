plugins {
    id("io.papermc.paperweight.userdev") version "2.0.0-beta.21"

    `maven-publish`
    `java-library`
}

rootProject.version = "9.8.6.4"
rootProject.group = "net.Zrips.CMILib"

repositories {
    maven("https://repo.papermc.io/repository/maven-public/")

    maven("https://repo.extendedclip.com/releases/")

    maven("https://repo.crazycrew.us/libraries/")

    maven("https://jitpack.io/")
}

dependencies {
    paperweight.paperDevBundle("26.1.2.build.+")

    compileOnly("io.netty:netty-transport:4.2.4.Final")
    compileOnly("me.clip:placeholderapi:2.11.6")
    compileOnly("com.Zrips.CMI:CMI-API:9.8.6.4")
}

publishing {
    repositories {
        maven {
            url = uri("https://repo.crazycrew.us/libraries/")

            credentials {
                username = System.getenv("GRADLE_USERNAME") ?: "N/A"
                password = System.getenv("GRADLE_PASSWORD") ?: "N/A"
            }

            authentication.create<BasicAuthentication>("basic")
        }
    }

    publications {
        create<MavenPublication>("mavenJava") {
            groupId = rootProject.group.toString()
            artifactId = rootProject.name
            version = rootProject.version.toString()

            from(components["java"])
        }
    }
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(25))

    withSourcesJar()
    withJavadocJar()
}

tasks {
    compileJava {
        options.encoding = Charsets.UTF_8.name()
        options.release.set(25)
    }
}