package by.training.task2.service;

/**
 * This class answer the question, it's possible to get positive sum of two numbers
 */
public class PositiveSum {
    public PositiveSum(){};

    /**
     * It's is executable method
     * @param args must contains 3 numbers
     * @return Statement can or can't.
     */
    public String execute(String []args){
        double []a=new double[3];
        for (int i=0;i<3;i++){
            a[i]= Double.parseDouble(args[i]);
        }
        if(a[0]+a[1]>0||a[1]+a[2]>0||a[2]+a[0]>0){
            return "This numbers can give positive sum.";
        }

        return "This numbers can't give positive sum.";
    }
}
