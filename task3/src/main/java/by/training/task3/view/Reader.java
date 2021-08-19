package by.training.task3.view;


import java.util.Scanner;

/**
 * This class realize connection with user through read console
 */

public class Reader {
    private final Scanner scanner = new Scanner(System.in);
    public Reader() {}

    /**
     * This method read String from the console
     * @return String line
     */
    public String getString() {
        return scanner.nextLine();
    }
}
