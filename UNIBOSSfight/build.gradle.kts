plugins {
    java
    application
    id("org.openjfx.javafxplugin") version "0.0.13"
    id("org.danilopianini.gradle-java-qa") version "1.0.0"
    // id("com.github.spotbugs") version "5.0.13"
    // id("de.aaschmid.cpd") version "3.3"

}

javafx {
    modules("javafx.controls", "javafx.fxml")
}


group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

//To run: ./gradlew -PmainClass=Main run
application {
    mainClass.set(project.properties["mainClass"].toString())
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}
