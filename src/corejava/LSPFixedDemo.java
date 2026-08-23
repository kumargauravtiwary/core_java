package corejava;

public class LSPFixedDemo {

    public static void main(String[] args) {

        Shape rectangle = new Rectangle(20, 10);
        Shape square = new Square(10);

        System.out.println("Rectangle area: " + rectangle.area());
        System.out.println("Square area   : " + square.area());

        printArea(rectangle);
        printArea(square);
    }

    static void printArea(Shape shape) {
        System.out.println(
                shape.getClass().getSimpleName()
                + " area = "
                + shape.area()
        );
    }
}


// Common abstraction
interface Shape {

    int area();
}


// Rectangle
class Rectangle implements Shape {

    private final int width;
    private final int height;

    public Rectangle(int width, int height) {

        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException(
                    "Width and height must be positive"
            );
        }

        this.width = width;
        this.height = height;
    }

    @Override
    public int area() {
        return width * height;
    }
}


// Square
class Square implements Shape {

    private final int side;

    public Square(int side) {

        if (side <= 0) {
            throw new IllegalArgumentException(
                    "Side must be positive"
            );
        }

        this.side = side;
    }

    @Override
    public int area() {
        return side * side;
    }
}
