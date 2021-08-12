package by.training.task2.service;

import java.util.ArrayList;

import static java.lang.Math.pow;

/**
 * This class getting 3 number and if number less by zero raise to the fourth degree, else third.
 */

public class DegreeAction {
    public DegreeAction(){};

    /**
     * This method is executable
     * @param args must contains 3 number
     * @return array with numbers
     */
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
