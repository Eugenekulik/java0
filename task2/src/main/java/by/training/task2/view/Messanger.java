package by.training.task2.view;

import java.util.Locale;

public class Messanger {
    Locale locale;
    public Messanger(String lang, String country)
    {
        this.locale=new Locale(lang,country);
    }
    public void print(String msg) {
        System.out.println("MyProgramWork: " + msg);
    }
}
