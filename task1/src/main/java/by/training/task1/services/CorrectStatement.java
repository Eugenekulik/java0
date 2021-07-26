package by.training.task1.services;

import java.util.ArrayList;

public class CorrectStatement {
    private boolean trueFalse;
    public CorrectStatement()
    {
        trueFalse=false;
    }
    public void statement1(int number)
    {
        if(number < 100 && number > 9 && number % 2 == 0){
            trueFalse=true;
        }
        else{
            trueFalse=false;
        }
        System.out.println("Является ли число четным двузначным: "+ trueFalse);
    }
    public void statement2(int number)
    {
        if(number >999 && number<10000){
            int a = number /1000;
            int b = number /100 - a*10;
            int c = number /10 - a*100 - b*10;
            int d = number - a*1000 - b*100 - c*10;
            if(a+b==c+d){
                trueFalse=true;
            }
            else{
                trueFalse=false;
            }
        }
    }
    public void statement3(){
        if(number >99 && number<1000){
            int a = number /100;
            int b = number /10 - a*10;
            int c = number - a*100 - b*10;
            if((a+b+c)%2==0){
                trueFalse[1]=true;
            }
            else{
                trueFalse[1]=false;
            }
        }
    }
    public void statement4(){

    }
}
