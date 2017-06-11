package sample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Paint;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;

import java.util.Arrays;

import static sample.Transformations.above;
import static sample.Transformations.beside;

public class Main extends Application {

    static String LIZARD_FILENAME = "/lizard.path";
    static String LAMBDA_FILENAME = "/lambda.path";

    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("Functional Geometry");
        int centerx = 400;
        int centery = 300;

        // creates a SVGPath object
        SVGPath lizard1 = new SVGPath();
        lizard1.setContent(Utils.readResource(LIZARD_FILENAME));
        lizard1.setStroke(Paint.valueOf("black"));
        lizard1.setFill(Paint.valueOf("99FFAA"));
        lizard1.setRotate(240);
        lizard1.setTranslateX(centerx);
        lizard1.setTranslateY(centery);

        SVGPath lizard2 = new SVGPath();
        lizard2.setContent(Utils.readResource(LIZARD_FILENAME));
        lizard2.setStroke(Paint.valueOf("black"));
        lizard2.setFill(Paint.valueOf("8855FF"));
        lizard2.setTranslateX(centerx - 376);
        lizard2.setTranslateY(centery+ 12);

        SVGPath lizard3 = new SVGPath();
        lizard3.setContent(Utils.readResource(LIZARD_FILENAME));
        lizard3.setStroke(Paint.valueOf("black"));
        lizard3.setFill(Paint.valueOf("FF5577"));
        lizard3.setRotate(120);
        lizard3.setTranslateX(centerx - 198);
        lizard3.setTranslateY(centery - 320);

        SVGPath lambda = new SVGPath();
        lambda.setContent(Utils.readResource(LAMBDA_FILENAME));

        Group root = new Group(beside(lambda, above(lambda, lambda)));
//        Group root = new Group(lizard1, lizard2, lizard3);

        Scene scene = new Scene(root, 1000, 1000);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String args[]){
        System.out.println(Arrays.toString(args));
        if (args.length == 1) {
            LIZARD_FILENAME = args[0];
        }
        launch(args);
    }
}