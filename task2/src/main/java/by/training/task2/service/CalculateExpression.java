package by.training.task2.service;

/**
 * This class is servece class which calculate expression a*x*x+b*x+c
 */
public class CalculateExpression {
    public CalculateExpression(){}

    /**
     * This method do calculations.
     * String []args must contain 4 numbers corresponding a,b,c,x.
     * @param args
     * @return
     */
    public double execute(String  []args)
    {
        double []a = new double[4];
        for (int i=0;i<4;i++) {
            a[i]=Double.parseDouble(args[i]);
        }
        return a[0]*a[3]*a[3]+a[1]*a[3]+a[2];
    }
}
