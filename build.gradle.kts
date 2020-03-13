import org.apache.tools.ant.filters.ReplaceTokens

plugins {
    java
}

val pluginname = "Example Plugin"
group = "com.thatgamerblue.oprs"
version = "1.0.0"

val tokenSet = mapOf(
    "version" to version,
    "pluginid" to rootProject.name,
    "pluginname" to pluginname
)

repositories {
    mavenCentral()
    maven {
        url = uri("https://jitpack.io")
    }
    exclusiveContent {
        forRepository {
            maven {
                url = uri("https://raw.githubusercontent.com/open-osrs/hosting/master")
            }
        }
        filter {
            includeModule("com.openosrs.rxrelay3", "rxrelay")
        }
    }
    exclusiveContent {
        forRepository {
            maven {
                url = uri("https://repo.runelite.net")
            }
        }
        filter {
            includeModule("net.runelite", "discord")
        }
    }
}

object Versions {
    const val guice = "4.2.2"
    const val lombok = "1.18.12"
    const val pf4j = "3.2.0"
}

dependencies {
    annotationProcessor("org.projectlombok:lombok:${Versions.lombok}")
    annotationProcessor("org.pf4j:pf4j:${Versions.pf4j}")

    implementation("com.github.open-osrs.runelite:runelite-client:master-SNAPSHOT")

    implementation("com.google.inject:guice:${Versions.guice}:no_aop")
    implementation("org.projectlombok:lombok:${Versions.lombok}")
    implementation("org.pf4j:pf4j:${Versions.pf4j}")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_11
}

tasks.processResources {
    from(sourceSets.main.get().resources)
    filter(ReplaceTokens::class, "tokens" to tokenSet)
}

tasks.create<Sync>("processSources") {
    from(sourceSets.main.get().java)
    filter(ReplaceTokens::class, "tokens" to tokenSet)
    into("$buildDir/src")
}

tasks.compileJava {
    source = tasks.getByName("processSources").outputs.files.asFileTree
}