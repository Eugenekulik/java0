package by.training.task2.service;

import java.util.ArrayList;

public class CalculateFunctionSegment {
    public CalculateFunctionSegment(){};
    public double[] execute(String []args){
        double x;
        double a=Double.parseDouble(args[0]);
        double b=Double.parseDouble(args[1]);
        double h=Double.parseDouble(args[2]);
        if(h<=0||a>b){
            return new double[]{0.0};
        }
        ArrayList<Double> y = new ArrayList<Double>();
        for(x=a;x<=b;x+=h) {
            if (x > 2) {
                y.add(x);
            }
            else{
                y.add(-x);
            }
        }
        double []result=new double[y.size()];
        int i=0;
        for (Double v:y) {
            result[i]=v;
            i++;
        }
        return result;
    }
}
