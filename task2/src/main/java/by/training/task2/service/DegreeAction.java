package by.training.task2.service;

import java.util.ArrayList;

import static java.lang.Math.pow;

public class DegreeAction {
    public DegreeAction(){};
    public double[] execute(String[]args){
        double []a = new double[3];
        for (int i=0;i<3;i++) {
            a[i]=Double.parseDouble(args[i]);
        }
        for(int i=0;i<3;i++){
            if(a[i]<0){
                a[i]=pow(a[i],4);
            }
            else {
                a[i]=pow(a[i],3);
            }
        }
        return a;
    }
}
