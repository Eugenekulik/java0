package by.training.task2.view;

import java.util.Locale;

/**
 * This class give opportunity send messages for user.
 */
public class Messanger {
    Locale locale;
    public Messanger(){};
    public void print(String msg) {
        System.out.print("Task2:"+msg);
    }
}
