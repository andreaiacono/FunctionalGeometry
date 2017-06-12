package sample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Paint;
import javafx.scene.shape.SVGPath;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

import java.util.Arrays;

import static sample.Transformations.*;

public class Main extends Application {

    static String LIZARD_FILENAME = "/lizard.path";
    static String LAMBDA_FILENAME = "/lambda.path";
    static String FISH_FILENAME = "/fish.path";

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

        SVGPath fish = new SVGPath();
        fish.setContent(Utils.readResource(FISH_FILENAME));

        Shape fish2 = flipVertical(rotate45(half(fish)));
        Shape fish3 = rotate90(rotate90(rotate90((fish2))));

        Shape t = Shape.union(fish, Shape.union(fish2, fish3));

        Shape u = Shape.union(
                            Shape.union(fish2, rotate90(fish2)),
                            Shape.union(rotate180(fish2), rotate270(fish2))
                        );

        Shape image = beside(fish, above(rotate45(fish), flipHorizontal(fish)));

//        new Group(lizard1, lizard2, lizard3);
        stage.setScene(new Scene(new Group(u), 1000, 650));
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