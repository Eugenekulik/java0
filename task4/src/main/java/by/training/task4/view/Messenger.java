package by.training.task4.view;

import java.util.Locale;
import java.util.ResourceBundle;

public class Messenger {
    private ResourceBundle resourceBundle;
    public void print(String msg){
        System.out.println(msg);
    }
    public void printProperty(String key){
        print(resourceBundle.getString(key));
    }
    public void initBundle(String file, Locale locale){
        resourceBundle = ResourceBundle.getBundle(file,locale);
    }
}
