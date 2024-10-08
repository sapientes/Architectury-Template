import net.fabricmc.loom.api.LoomGradleExtensionAPI

plugins {
    java
    id("architectury-plugin") version "3.4-SNAPSHOT"
    id("dev.architectury.loom") version "1.6-SNAPSHOT" apply false
    id("com.github.johnrengelman.shadow") version "8.1.1" apply false
}

val minecraftVersion: String by extra
val yarnVersion: String by extra
val yarnNeoforgePatchVersion: String by extra
val modVersion: String by extra
val mavenGroup: String by extra
val customArchivesBaseName: String by extra

architectury {
    minecraft = minecraftVersion
}

allprojects {
    apply(plugin = "java")

    version = modVersion
    group = mavenGroup
}

subprojects {
    apply(plugin = "dev.architectury.loom")
    apply(plugin = "architectury-plugin")

    base {
        archivesName.set(customArchivesBaseName + project.name)
    }

    configure<LoomGradleExtensionAPI> {
        silentMojangMappingsLicense()
    }

    dependencies {
        "minecraft"("com.mojang:minecraft:$minecraftVersion")
        "mappings" (project.the<LoomGradleExtensionAPI>().layered {
            mappings("net.fabricmc:yarn:${yarnVersion}:v2")
            mappings("dev.architectury:yarn-mappings-patch-neoforge:${yarnNeoforgePatchVersion}")
        })
    }

    java {
        withSourcesJar()

        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }

    tasks.withType<JavaCompile> {
        options.release.set(21)
    }
}
