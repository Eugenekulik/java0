package by.training.task2.service;

public class PositiveSum {
    public PositiveSum(){};
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
