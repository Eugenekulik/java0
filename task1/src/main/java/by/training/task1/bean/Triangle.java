package by.training.task1.bean;

import java.util.Arrays;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Triangle {
    private Point[] points;

    public double[] getSides() {
        return sides;
    }

    private double[] sides;

    public Triangle(double []numb){//TODO: numb.length
        points=new Point[3];
        points[0].setX(numb[0]);
        points[0].setY(numb[1]);
        points[1].setX(numb[2]);
        points[1].setY(numb[3]);
        points[2].setX(numb[4]);
        points[2].setY(numb[5]);
        sides = new double[3];
        sides[0]=sqrt(pow((points[0].getX()-points[1].getX()),2) + pow((points[0].getY()-points[1].getY()),2));
        sides[1]=sqrt(pow((points[1].getX()-points[2].getX()),2)+pow((points[1].getY()-points[2].getY()),2));
        sides[3]=sqrt(pow((points[2].getX()-points[0].getX()),2)+pow((points[2].getY()-points[0].getY()),2));
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Triangle triangle = (Triangle) o;
        return Arrays.equals(points, triangle.points) && Arrays.equals(sides, triangle.sides);
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(points);
        result = 31 * result + Arrays.hashCode(sides);
        return result;
    }

    @Override
    public String toString() {
        return "Triangle{" +
                "points=" + Arrays.toString(points) +
                ", sides=" + Arrays.toString(sides) +
                '}';
    }
}
