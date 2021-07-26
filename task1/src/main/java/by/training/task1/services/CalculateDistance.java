package by.training.task1.services;

import by.training.task1.entities.Point;

import java.util.Scanner;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;

public class CalculateDistance {
    public static double betweenPoints()
    {
        Scanner scanPoint = new Scanner(System.in);
        System.out.println("First point x = ");
        Point a=new Point();
        Point b=new Point();
        a.setX(Integer.parseInt(scanPoint.next()));
        a.setY(Integer.parseInt(scanPoint.next()));
        b.setX(Integer.parseInt(scanPoint.next()));
        b.setY(Integer.parseInt(scanPoint.next()));
        double distance=0;
        distance=sqrt(abs(a.getX()-b.getX())^2-abs(a.getY()-b.getY())^2);
        return distance;
    }
}
