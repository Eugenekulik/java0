package by.training.task3.view;

import java.util.Scanner;

public class Reader {
    private Scanner scanner =  new Scanner(System.in);
    public Reader(){};
    public String getString(){
        return scanner.nextLine();
    }
}
