package by.training.task2.service;

import java.util.ArrayList;

/**
 * This class calculate function on line segment [a,b] with step h
 */

public class CalculateFunctionSegment {
    public CalculateFunctionSegment(){};

    /**
     * This method do calculations
     * @param args must contains 3 numbers: a,b,h.
     * @return array double result
     * @throws ServiceException
     */
    public double[] execute(String []args) throws ServiceException {
        double x;
        double a=Double.parseDouble(args[0]);
        double b=Double.parseDouble(args[1]);
        double h=Double.parseDouble(args[2]);
        if(h<=0){
            throw new ServiceException("Step h can't be less than 0");
        }
        if(a>b){
            throw new ServiceException("a can't be more than b");
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
