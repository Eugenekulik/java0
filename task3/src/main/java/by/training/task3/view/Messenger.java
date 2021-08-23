package by.training.task3.view;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * This class realize connection with user through output message on console
 */
public class Messenger {
    public Messenger(){}
    private ResourceBundle rb;
    /**
     * Print message in console
     * @param msg String message
     */
    public void print(String msg){
        System.out.println(msg);
    }
    public void resourceBundleInit(ResourceBundle rb){
        this.rb = rb;
    }
    public void printProperty(String key){
        print(rb.getString(key));
    }
}
