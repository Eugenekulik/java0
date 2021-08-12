package by.training.task2.view;

import java.util.Scanner;

/**
 * This class give opportunity to recieve values and command from the user.
 */
public class Reader {
    private Scanner scanner= new Scanner(System.in);
    public String getString(String msg)
    {
        System.out.print(msg+"\nTask2: ");
        return scanner.nextLine();
    }
}
