plugins {
    java
    application
    id("org.danilopianini.gradle-java-qa") version "1.0.0"
    //id("com.github.spotbugs") version "5.0.13"
    //id("de.aaschmid.cpd") version "3.3"
    id("com.github.johnrengelman.shadow") version "7.1.2"

}

val javaFXModules = listOf(
        "base",
        "controls",
        "fxml",
        "swing",
        "graphics"
)

val supportedPlatforms = listOf("linux", "mac", "win")


group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

//To run: ./gradlew -PmainClass=Main run
application {
    mainClass.set("app.Main")
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")

    implementation ("com.google.code.gson:gson:2.10.1")
    compileOnly("com.github.spotbugs:spotbugs-annotations:4.7.3")

    val javaFxVersion = 19
    for (platform in supportedPlatforms) {
        for (module in javaFXModules) {
            implementation("org.openjfx:javafx-$module:$javaFxVersion:$platform")
        }
    }
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}
