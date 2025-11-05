public enum  Shape {
    CIRCLE,
    SQUARE,
    TRIANGLE;

    //a static method that returns if a point is inside the shape (x,y) given the shape size
    public static boolean isPointInsideShape(Shape shape, double size, double x, double y) {
        switch (shape) {
            case CIRCLE:
                return (x * x + y * y) <= (size * size) / 4;
            case SQUARE:
                return Math.abs(x) <= size / 2 && Math.abs(y) <= size / 2;
            case TRIANGLE:
                return y >= -Math.sqrt(3) * x - size / 2 && y >= Math.sqrt(3) * x - size / 2 && y <= size * Math.sqrt(3) / 2;
            default:
                return false;
        }
    }
}