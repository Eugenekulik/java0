package by.training.task2.service;

import java.util.ArrayList;

import static java.lang.Math.sin;

/**
 * This class calculate function on line segment [a,b] with step h and print as table
 */

public class CalculateFunctionTable {
    public CalculateFunctionTable(){};

    /**
     * This method do calculations.
     * @param args must contains 3 numbers: a,b,h.
     * @return array containing x,y.
     * @throws ServiceException
     */
    public double[] execute(String[] args) throws ServiceException{
        double a = Double.parseDouble(args[0]);
        double b = Double.parseDouble(args[1]);
        double h = Double.parseDouble(args[2]);
        if(h<=0){
            throw new ServiceException("Step h can't be less than 0");
        }
        if(a>b){
            throw new ServiceException("a can't be more than b");
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
