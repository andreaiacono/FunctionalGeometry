package sample;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Paint;
import javafx.scene.shape.SVGPath;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

import java.util.Arrays;

import static sample.Transformations.*;

public class Main extends Application {

    private static final double INITIAL_SIZE = 600;
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
        lizard2.setTranslateY(centery + 12);

        SVGPath lizard3 = new SVGPath();
        lizard3.setContent(Utils.readResource(LIZARD_FILENAME));
        lizard3.setStroke(Paint.valueOf("black"));
        lizard3.setFill(Paint.valueOf("FF5577"));
        lizard3.setRotate(120);
        lizard3.setTranslateX(centerx - 198);
        lizard3.setTranslateY(centery - 320);

        SVGPath fish1 = new SVGPath();
        fish1.setContent(Utils.readResource(FISH_FILENAME));
        Shape fish = translate(fish1, 100, 60);

        Shape fish2 = flipVertical(rotate45(half(fish)));
        Shape fish3 = rotate90(rotate90(rotate90((fish2))));

        Shape t = union(
                fish,
                Shape.union(
                        translate(fish2, fish2.getLayoutBounds().getWidth() - 20, -17), //-fish2.getLayoutBounds().getWidth()),
                        translate(fish3, fish2.getLayoutBounds().getWidth() * 0.45 - 10, -fish3.getLayoutBounds().getHeight() * 0.35 - 5)
                )
        );

//        Shape u = union(
//                union(
//                        translate(fish2, 100, 0),
//                        translate(rotate90(fish2), 20, 80)
//                ),
//                union(
//                        translate(rotate180(fish2), -60, 0),
//                        translate(rotate270(fish2), 20, -80)
//                )
//        );
        Shape v = cycle((t));
////
//
//        Shape image = beside(fish, above(rotate45(fish), flipHorizontal(fish)));

//        Shape quartet = quartet(Transformations.clone(u), Transformations.clone(u), Transformations.clone(u), Transformations.clone(u));
//        Shape quartet = quartet(Transformations.clone(u), Transformations.clone(u), Transformations.clone(u), Transformations.clone(u));
//        new Group(lizard1, lizard2, lizard3);
        Group root = new Group(quartet(v, v,v, v));

        stage.setScene(new Scene(root, INITIAL_SIZE, INITIAL_SIZE));
        ChangeListener<Number> stageSizeListener = (observable, oldValue, newValue) -> {
            double newSize = Math.min(stage.getHeight(), stage.getWidth());
            double ratio = newSize / INITIAL_SIZE;
            root.setScaleX(ratio);
            root.setScaleY(ratio);
            root.setTranslateX(ratio*50);
            root.setTranslateY(ratio*50);
        };

        stage.widthProperty().addListener(stageSizeListener);
        stage.heightProperty().addListener(stageSizeListener);
//        stage.setScene(new Scene(new Group(above(fish1, fish1)), 400, 350));
        stage.show();
    }

    public static void main(String args[]) {
        System.out.println(Arrays.toString(args));
        if (args.length == 1) {
            LIZARD_FILENAME = args[0];
        }
        launch(args);
    }
}