package by.training.task2.service;

/**
 * This class reverce variables if x<y.
 */
public class Redistribution {
    public Redistribution(){};

    /**
     * It's executable method
     * @param args must contains 2 numbers
     * @return x,y
     */
    public double[] execute(String []args){
        double x=Double.parseDouble(args[0]);
        double y=Double.parseDouble(args[1]);
        if(x<y){
            double temp = x;
            x=y;
            y=temp;
        }
        return new double[]{x,y};
    }
}
