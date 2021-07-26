package by.training.task1.services;

import java.util.Scanner;

public class Menu {

    private Menu() {
    }

    public static void menurun(int choose){
        switch(choose){
            case 1:
                CalculateDistance.betweenPoints();
                break;
            case 2:

                break;
            default:
                System.out.println("invalid number");
                break;
        }
    }
}
