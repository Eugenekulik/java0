package by.training.task1.view;

import by.training.task1.bean.Point;

import java.util.Scanner;

public class Interaction {
    Scanner scan;
    public Interaction(){
        scan = new Scanner(System.in);
    };

    public Point getPoint(String msg)
    {
        System.out.println(msg);
        double x = getNumberD("write x: ");
        double y = getNumberD("write x: ");
        return new Point(x,y);
    }
    public double getNumberD(String msg){
        System.out.println(msg);
        while(true) {
            if (scan.hasNextDouble()) {
                return Double.parseDouble(scan.next());
            } else {
                System.out.println(scan.next() + " invalid value, try again: ");

            }
        }
    }

    public int getNumberI(String msg){
        System.out.println(msg);
        while(true) {
            if (scan.hasNextInt()) {
                return Integer.parseInt(scan.next());
            } else {
                System.out.println(scan.next() + " invalid value, try again: ");

            }
        }
    }
}
