package by.training.task2.service;

import java.util.ArrayList;

import static java.lang.Math.sin;

public class CalculateFunctionTable {
    public CalculateFunctionTable(){};
    public double[] execute(String[] args) throws ArithmeticException{
        double a = Double.parseDouble(args[0]);
        double b = Double.parseDouble(args[1]);
        double h = Double.parseDouble(args[2]);
        if(h==0){
            throw new ArithmeticException("h can't be 0");
        }
        ArrayList<Double> c=new ArrayList<>();
        for (double x = a; x <= b; x += h) {
            c.add(x);
            c.add(sin(x));
        }
        Double[] r = c.toArray(new Double[c.size()]);
        double []result=new double[r.length];
        for(int i=0;i<r.length;i++){
            result[i]=r[i];
        }
        return result;
    }
}
