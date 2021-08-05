package by.training.task2.service;

public class CalculateExpression {
    public CalculateExpression(){}
    public double execute(String  []args)
    {
        double []a = new double[4];
        for (int i=0;i<4;i++) {
            a[i]=Double.parseDouble(args[i]);
        }
        return a[0]*a[3]*a[3]+a[1]*a[3]+a[2];
    }
}
