package by.training.task2.service;

public class MathTask {
    public MathTask(){};
    public int[] execute(){
        int a = 10;
        int b = 10;
        while(a<100){
            while(b<100){
                if((a+b*100)%49==0 && (a*100+b)%99==0){
                    return new int[]{a,b};
                }
                b++;
            }
            b=0;
            a++;

        }
        return new int[]{0,0};
    }
}
