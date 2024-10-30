plugins {
    id("java")


    application
    id("org.openjfx.javafxplugin") version "0.1.0"

}
javafx {
    version = "22.0.1"
    modules = listOf("javafx.controls", "javafx.graphics", "javafx.fxml", "javafx.media", "javafx.swing")
}
group = "ies.pedro"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {

    implementation("io.github.palexdev:materialfx:11.17.0")
    implementation("org.controlsfx:controlsfx:11.2.1")

   implementation("fr.brouillard.oss:cssfx:11.5.1")
    implementation("com.dlsc.formsfx:formsfx-core:11.6.0")
    implementation("com.dlsc.formsfx:formsfx:11.6.0")
    implementation("org.kordamp.ikonli:ikonli-javafx:12.3.1")
    implementation("org.kordamp.ikonli:ikonli-antdesignicons-pack:12.3.1")

    //base de datos
    implementation ("org.apache.derby:derby:10.16.1.1")
    //controles extras
    implementation("org.controlsfx:controlsfx:11.2.1")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

application {
    // Define the main class for the application.
    mainClass = "ies.pedro.Main"
}