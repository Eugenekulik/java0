package by.training.task1.service;

public class ElapcedTime {
    private ElapcedTime(){}

    public static String calculate(int h,int m, int s, int p, int q, int r){
        if(h>23||m>59||s>59||q>59||r>59){
            return "invalid data";
        }
        int num = (h+p)*3600 + (m+q)*60 + s + r;
        while(num>86399){
            num -= 86400;
        }
        return TimeConverter.numToTime(num);
    }
}
