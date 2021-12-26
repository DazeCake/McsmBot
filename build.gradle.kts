plugins {
    val kotlinVersion = "1.5.10"
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.serialization") version kotlinVersion

    id("net.mamoe.mirai-console") version "2.8.3"
}

group = "moe.dazecake"
version = "0.0.1"

repositories {
    mavenLocal()
    maven("https://maven.aliyun.com/repository/public")
    mavenCentral()
}

dependencies{
    implementation("com.google.code.gson:gson:2.8.9")
}
tasks.create("buildJarToEnv",Jar::class){
    dependsOn("buildPlugin")
    doLast{
        val buildPluginTask = tasks.getByName("buildPlugin",Jar::class)
        val buildPluginFile = buildPluginTask.archiveFile.get().asFile
        project.buildDir.resolve("D:\\MiraiProjects\\mcl-1.2.2\\plugins").also {
            it.mkdirs()
        }.resolve("McsmBot-0.0.1.mirai.jar").let {
            buildPluginFile.copyTo(it,true)
        }
    }
}