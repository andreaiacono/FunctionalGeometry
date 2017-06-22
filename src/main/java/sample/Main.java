package sample;

import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.SVGPath;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Arrays;

import static sample.Transformations.*;

public class Main extends Application {

    private static final double INITIAL_SIZE = 600;
    static String LIZARD_FILENAME = "/lizard.path";
    static String LAMBDA_FILENAME = "/lambda.path";
    static String FISH_FILENAME = "/fish.path";
    boolean isRotating = false;
    RotateTransition rt;

    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("Functional Geometry");
        SVGPath fish1 = new SVGPath();
        fish1.setContent(Utils.readResource(FISH_FILENAME));

        Shape fish = Transformations.clone(fish1);
        Shape fish2 = flipVertical(rotate45(half(fish1)));
        Shape fish3 = rotate90(rotate90(rotate90((fish2))));

        Shape t = union(
                fish,
                Shape.union(
                        translate(fish2, 145, -15), //-fish2.getLayoutBounds().getWidth()),
                        translate(fish3, 65, -95)
                )
        );

        Shape u = union(
                union(
                        translate(fish2, 100, 0),
                        translate(rotate90(fish2), 20, 80)
                ),
                union(
                        translate(rotate180(fish2), -60, 0),
                        translate(rotate270(fish2), 20, -80)
                )
        );


        Shape v = cycle((t));
//
//
//        Shape image = beside(fish, above(rotate45(fish), flipHorizontal(fish)));

//        Shape quartet = quartet(Transformations.clone(u), Transformations.clone(u), Transformations.clone(u), Transformations.clone(u));
//        Shape quartet = quartet(Transformations.clone(u), Transformations.clone(u), Transformations.clone(u), Transformations.clone(u));
        Bounds bounds = fish.getBoundsInParent();
        Rectangle rectangle = new Rectangle(bounds.getMinX(), bounds.getMinY(), bounds.getWidth(), bounds.getHeight());
        rectangle.setFill(Color.TRANSPARENT);
        rectangle.setStroke(Paint.valueOf("00FF00"));
//        Group root = new Group(fish, rectangle);
//        Group root = new Group(u);
//        Group root = new Group(quartet(u, u, u, u));
//        Group root = new Group(side(fish, 2));
        Group root = new Group(corner(fish, 2));
//        Group root = new Group(squareLimit(u, 1));

//
        System.out.println(fish.getLayoutBounds());
        Scene scene = new Scene(root, INITIAL_SIZE, INITIAL_SIZE);
        stage.setScene(scene);
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

        rt = new RotateTransition(Duration.seconds(20), root);
        rt.setToAngle(720);
        rt.setCycleCount(Timeline.INDEFINITE);
        rt.setAutoReverse(true);


        scene.addEventFilter(MouseEvent.MOUSE_PRESSED, rotationHandler);
        stage.show();

    }

    EventHandler rotationHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            if (isRotating) {
                rt.stop();
            } else {
                rt.play();
            }
            isRotating = !isRotating;
        }
    };

    public static void main(String args[]) {
        System.out.println(Arrays.toString(args));
        if (args.length == 1) {
            LIZARD_FILENAME = args[0];
        }
        launch(args);
    }
}