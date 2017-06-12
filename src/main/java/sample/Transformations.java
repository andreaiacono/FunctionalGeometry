package  sample;

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
    private static Shape translate(Shape image, double x, double y) {
        image.setTranslateX(x);
        image.setTranslateY(y);
        return image;
    }
    public static Shape translateX(Shape image, double x) { return translate(image, x, 0); }
    public static Shape translateY(Shape image, double y) { return translate(image, 0, y); }
    public static Shape translateXY(Shape image, double x, double y) { return translate(image, x, y); }

    // rotations
    private static Shape rotate(Shape image, double angle) {
        image.setRotate(angle);
        return image;
    }
    public static Shape rotate90(Shape image) { return rotate(image, -90); }
    public static Shape rotate45(Shape image) { return rotate(image, -45); }


    // compositions
    public static Shape flip(Shape image) {
        image.setRotate(-90d);
        return image;
    }

//    public static Shape beside(Shape image1, Shape image2) {
//        image1.setScaleX(-0.5d);
//        image2.setScaleX(-0.5d);
//        image2 = translateX(image2, 100);
//        SVGPath newPath = new SVGPath();
//        newPath.setContent(image1.getContent() + " " + image2.getContent());
//        return newPath;
//    }

    public static Shape above(Shape image1, Shape image2) {
//        Shape temp1 = clone(image1);
//        Shape temp2 = clone(image2);
        image1.setTranslateY(100);
        image2.setTranslateY(-100);
        image2.setScaleY(0.5d);
        image1.setScaleY(0.5d);
        return Shape.union(image1, image2);
    }

    public static Shape beside(Shape image1, Shape image2) {
//        Shape temp1 = clone(image1);
//        Shape temp2 = clone(image2);
        image1.setTranslateX(100);
        image2.setTranslateX(-50);
        image2.setScaleX(0.5d);
        image1.setScaleX(0.5d);
        return Shape.union(image1, image2);
    }

    private static Shape clone(Shape image) {
        SVGPath newImage = new SVGPath();
        newImage.setContent(((SVGPath)image).getContent());
        return newImage;
    }

    public static Shape flipHorizontal(Shape image) {
        image.setScaleX(-1);
        return image;
    }

    public static Shape flipVertical(Shape image) {
        image.setScaleY(-1);
        return image;
    }

}
