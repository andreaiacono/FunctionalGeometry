package  sample;

import javafx.scene.Node;
import javafx.scene.shape.SVGPath;
import javafx.scene.shape.Shape;

public class Transformations {

    public static String path = "";

    /*
    lambda2.setContent(path);
        lambda2.setLayoutX(100d);
        lambda2.setRotate(170);
        lambda2.setScaleX(1);
        lambda2.setScaleY(0.5);
        lambda2.usesMirroring();

     */

    // translations
    private static SVGPath translate(SVGPath image, double x, double y) {
        image.setTranslateX(x);
        image.setTranslateY(y);
        return image;
    }
    public static SVGPath translateX(SVGPath image, double x) { return translate(image, x, 0); }
    public static SVGPath translateY(SVGPath image, double y) { return translate(image, 0, y); }
    public static SVGPath translateXY(SVGPath image, double x, double y) { return translate(image, x, y); }

    // rotations
    private static SVGPath rotate(SVGPath image, double angle) {
        image.setRotate(angle);
        return image;
    }
    public static SVGPath rotate90(SVGPath image) { return rotate(image, -90); }
    public static SVGPath rotate45(SVGPath image) { return rotate(image, -45); }


    // compositions
    public static SVGPath flip(SVGPath image) {
        image.setRotate(-90d);
        return image;
    }

    public static SVGPath beside(SVGPath image1, SVGPath image2) {
        image1.setScaleX(-0.5d);
        image2.setScaleX(-0.5d);
        image2 = translateX(image2, 100);
        SVGPath newPath = new SVGPath();
        newPath.setContent(image1.getContent() + " " + image2.getContent());
        return newPath;
    }

    public static Node above(SVGPath image1, SVGPath image2) {
        SVGPath temp1 = clone(image1);
        SVGPath temp2 = clone(image2);
        temp1.setScaleY(-0.5d);
        temp2.setScaleY(-0.5d);
        temp2 = translateY(image2, 100);
        return Shape.union(temp1, temp2);
    }

    private static SVGPath clone(SVGPath image) {
        SVGPath newImage = new SVGPath();
        newImage.setContent(image.getContent());
        return newImage;
    }

}
