package sample;

import javafx.scene.paint.Paint;
import javafx.scene.shape.SVGPath;
import javafx.scene.shape.Shape;

public class Transformations {

    private static Shape blank = new SVGPath();

    public static Shape union(Shape image1, Shape image2) {
        return Shape.union(image1, image2);
    }

    public static Shape clone(Shape image) {
        return union(image, blank);
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
    public static Shape translate(Shape image, double x, double y) {
        Shape temp = clone(image);
        temp.setTranslateX(x);
        temp.setTranslateY(y);
        return temp;
    }

    public static Shape translateX(Shape image, double x) {
        return translate(image, x, 0);
    }

    public static Shape translateY(Shape image, double y) {
        return translate(image, 0, y);
    }

    // size
    private static Shape scale(Shape image, double x, double y) {
        Shape temp = clone(image);
        temp.setScaleX(x);
        temp.setScaleY(y);
        return temp;
    }

    public static Shape half(Shape image) {
        return scale(image, 0.71, 0.71);
    }

    // rotations
    private static Shape rotate(Shape image, double angle) {
        Shape temp = clone(image);
        temp.setRotate(angle);
        return temp;
    }

    public static Shape rotate270(Shape image) {
        return rotate(image, 270);
    }

    public static Shape rotate180(Shape image) {
        return rotate(image, 180);
    }

    public static Shape rotate90(Shape image) {
        return rotate(image, 90);
    }

    public static Shape rotate45(Shape image) {
        return rotate(image, 45);
    }

    // compositions
    public static Shape above(Shape image1, Shape image2) {
        Shape temp1 = clone(image1);
        Shape temp2 = clone(image2);
        temp1.setTranslateY(58);
        temp2.setTranslateY(-68);
        temp1.setScaleY(0.5d);
        temp2.setScaleY(0.5d);
        return union(temp1, temp2);
    }

    public static Shape beside(Shape image1, Shape image2) {
        Shape temp1 = clone(image1);
        Shape temp2 = clone(image2);
        temp1.setTranslateX(58);
        temp2.setTranslateX(-68);
        temp2.setScaleX(0.5d);
        temp1.setScaleX(0.5d);
        return union(temp1, temp2);
    }

    public static Shape quartet(Shape image1, Shape image2, Shape image3, Shape image4) {
        return above(beside(image1, image2), beside(image3, image4));
    }

    public static Shape cycle(Shape image) {
        return quartet(
                translate(rotate90(image), 16, 2),
                translate(rotate180(image), 10, 20),
                translate(image, 0, -10),
                translate(rotate270(image), -2,10 )
        );
    }

    public static Shape side1(Shape image) {
        return quartet(blank, blank, rotate90(image), image);
    }

    public static Shape side(Shape image, int n) {
        if (n == 1) {
            return side1(image);
        }
        return quartet(side(image, n - 1), side(image, n - 1), rotate90(image), image);
    }

    public static Shape corner1(Shape image) {
        return quartet(blank, blank, blank, image);
    }

    public static Shape corner(Shape image, int n) {
        if (n == 1) {
            return corner1(image);
        }
        return quartet(corner(image, n - 1), side(image, n - 1), rotate90(side(image, n - 1)), image);
    }

    public static Shape nonet(Shape image1_1, Shape image1_2, Shape image1_3,
                       Shape image2_1, Shape image2_2, Shape image2_3,
                       Shape image3_1, Shape image3_2, Shape image3_3) {
        
        return above(beside(image1_1, beside(image1_2, image1_3)),
                above(beside(image2_1, beside(image2_2, image2_3)),
                        beside(image3_1, beside(image3_2, image3_3))));
        
    }
    
    public static Shape squareLimit(Shape image, int n) {
        return nonet(corner(image, n), side(image, n), rotate90(rotate90(rotate90(corner(image, n)))),
                rotate90(side(image, n)), image, rotate90(rotate90(rotate90(side(image, n)))),
                rotate90(corner(image, n)), rotate90(rotate90(side(image, n))), rotate90(rotate90(corner(image, n))));

    }
}
