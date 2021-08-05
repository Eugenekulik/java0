package by.training.task2.service;

import static java.lang.Math.pow;

public class CalculateFunction {
    public CalculateFunction(){};
    public double execute(String []args)throws ArithmeticException{
        double x = Double.parseDouble(args[0]);
        if(x<3){
            if(pow(x,3)==6){
                throw new ArithmeticException("Divide by zero");
            }
            return 1/(pow(x,3)-6);
        }
        else{
            return -pow(x,2)+x*3+9;
        }
    }

}
