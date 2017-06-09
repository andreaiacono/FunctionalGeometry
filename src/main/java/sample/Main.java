package sample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;

import java.io.File;
import java.nio.file.Files;

import static sample.Transformations.*;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("Functional Geometry");

        // creates a SVGPath object
        SVGPath fish = new SVGPath();
        File file = new File(getClass().getClassLoader().getResource("fish.path").getFile());
        String fishPath = new String(Files.readAllBytes(file.toPath()));
//        String two = new String(Files.readAllBytes(Paths.get("/Users/aiacono/Downloads/lambda.path")));
        fish.setContent(fishPath);

        Group root = new Group(above(fish, fish));

        Scene scene = new Scene(root, 400, 400);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String args[]){
        launch(args);
    }





}