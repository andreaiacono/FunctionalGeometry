package  sample;

import javafx.scene.shape.Shape;

public class Transformations {

    private static Shape clone(Shape image) {
        return Shape.union(image, image);
    }

    public static Shape flipHorizontal(Shape image) {
        Shape temp = clone(image);
        temp.setScaleX(-1);
        return temp;
    }

    public static Shape flipVertical(Shape image) {
        Shape temp = clone(image);
        temp.setScaleY(-1);
        return temp;
    }

    // translations
    private static Shape translate(Shape image, double x, double y) {
        Shape temp = clone(image);
        temp.setTranslateX(x);
        temp.setTranslateY(y);
        return temp;
    }
    public static Shape translateX(Shape image, double x) { return translate(image, x, 0); }
    public static Shape translateY(Shape image, double y) { return translate(image, 0, y); }

    // size
    private static Shape scale(Shape image, double x, double y) {
        Shape temp = clone(image);
        temp.setScaleX(x);
        temp.setScaleY(y);
        return temp;
    }

    public static Shape half(Shape image) { return scale(image, 0.5, 0.5); }

    // rotations
    private static Shape rotate(Shape image, double angle) {
        Shape temp = clone(image);
        temp.setRotate(angle);
        translate(temp, temp.getLayoutBounds().getWidth()/2, temp.getLayoutBounds().getHeight()/2);
        return temp;
    }
    public static Shape rotate270(Shape image) { return rotate(image, 270); }
    public static Shape rotate180(Shape image) { return rotate(image, 180); }
    public static Shape rotate90(Shape image) { return rotate(image, 90); }
    public static Shape rotate45(Shape image) { return rotate(image, 45); }

    // compositions
    public static Shape above(Shape image1, Shape image2) {
        Shape temp1 = clone(image1);
        Shape temp2 = clone(image2);
        translateY(temp1, 100);
        translateY(temp2, -100);
        scale(temp2, 0.5d,1);
        scale(temp1, 0.5d,1);
        return Shape.union(temp1, temp2);
    }

    public static Shape beside(Shape image1, Shape image2) {
        Shape temp1 = clone(image1);
        Shape temp2 = clone(image2);
        translateX(temp1, 100);
        translateX(temp2, -50);
        scale(temp2, 0.5d,1);
        scale(temp1, 0.5d,1);
        return Shape.union(temp1, temp2);
    }
}
