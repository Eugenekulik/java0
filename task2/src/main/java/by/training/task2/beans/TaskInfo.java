package by.training.task2.beans;

import java.util.Locale;
import java.util.ResourceBundle;

public class TaskInfo{
    ResourceBundle rb;
    public TaskInfo(){};
    public String getInfo(Locale l){
        rb=ResourceBundle.getBundle("TasksInfo", l);
        return "CALCEXP: "+rb.getString("task1.description") +"\n"+
                "CALCFUNC: "+rb.getString("task2.description") +"\n"+
                "CALCFUNCSEGM: "+rb.getString("task3.description") +"\n"+
                "CALCFUNCTABLE"+rb.getString("task4.description") +"\n"+
                "CURRSTR: "+rb.getString("task5.description") +"\n"+
                "DEGREEACT: "+rb.getString("task6.description") +"\n"+
                "MATHTASK: "+rb.getString("task7.description") +"\n"+
                "POSSUM: "+rb.getString("task8.description") +"\n"+
                "PRODSEQ: "+rb.getString("task9.description") +"\n"+
                "REDISTR: "+rb.getString("task10.description")+"\n"+
                "Exit";
    }
}
