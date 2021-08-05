package by.training.task2.service;

public class Redistribution {
    public Redistribution(){};
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
