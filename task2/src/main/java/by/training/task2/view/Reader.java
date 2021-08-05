package by.training.task2.view;

import java.util.Scanner;

public class Reader {
    private Scanner scanner= new Scanner(System.in);
    public String getString(String msg)
    {
        System.out.println(msg);
        return scanner.next();
    }
}
