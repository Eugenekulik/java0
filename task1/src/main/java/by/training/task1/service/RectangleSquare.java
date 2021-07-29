package by.training.task1.service;

public class RectangleSquare {
    private RectangleSquare(){}
    public static double square(double width)
    {
        if(width > 0) {
            return 2 * width * width;
        }
        else {
            return 0;
        }
    }
}
