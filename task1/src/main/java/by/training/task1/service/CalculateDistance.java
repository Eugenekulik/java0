package by.training.task1.service;

import by.training.task1.bean.Point;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class CalculateDistance {
    public static double betweenPoints(Point a, Point b) {

        double distance = sqrt(pow((a.getX() - b.getX()), 2) + pow((a.getY() - b.getY()), 2));
        return distance;
    }
}
