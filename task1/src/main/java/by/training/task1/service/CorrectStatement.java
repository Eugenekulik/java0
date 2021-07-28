package by.training.task1.service;


import by.training.task1.bean.Point;
import by.training.task1.bean.Triangle;
import by.training.task1.view.Interaction;

public class CorrectStatement {
    private boolean trueFalse;
    private Interaction user;
    private static String[] statementInfo;
    public  boolean menuState()
    {
        int state=0;
        boolean correct=false;
        state=user.getNumberI("choose statement: ");
        while(!correct) {
            switch (state) {
                case 0:
                    correct=true;
                    break;
                case 1:
                    System.out.println("#############################\n" + statementInfo[0]);
                    this.statement1(user.getNumberI("write number: "));
                    correct=true;
                    break;
                case 2:
                    System.out.println("#############################\n" + statementInfo[1]);
                    this.statement2(user.getNumberI("write number: "));
                    correct=true;
                    break;
                case 3:
                    System.out.println("#############################\n" + statementInfo[2]);
                    this.statement3(user.getNumberI("write number: "));
                    correct=true;
                    break;
                case 4:
                    System.out.println("#############################\n" + statementInfo[3]);
                    this.statement4(user.getPoint("create point"),user.getNumberI("write first x: "),
                            user.getNumberI("write second x: "));
                    correct=true;
                    break;
                case 5:
                    System.out.println("#############################\n" + statementInfo[4]);
                    this.statement5(user.getNumberI("write number: "));
                    correct=true;
                    break;
                case 6:
                    System.out.println("#############################\n" + statementInfo[5]);
                    double[] numbs ={user.getNumberD("write point a, coordinate x: "),
                            user.getNumberD("write point a, coordinate y: "),
                            user.getNumberD("write point b, coordinate x: "),
                            user.getNumberD("write point b, coordinate y: "),
                            user.getNumberD("write point c, coordinate x: "),
                            user.getNumberD("write point c, coordinate y: ")};
                    Triangle tr=new Triangle(numbs);
                    this.statement6(tr);
                    correct=true;
                    break;
                case 7:
                    System.out.println("#############################\n" + statementInfo[6]);
                    this.statement7(user.getNumberI("write number: "));
                    correct=true;
                    break;
                case 8:
                    System.out.println("#############################\n" + statementInfo[7]);
                    this.statement8(user.getNumberI("write number: "),user.getNumberI("write number: "));
                    correct=true;
                    break;
                case 9:
                    System.out.println("#############################\n" + statementInfo[8]);
                    this.statement9(user.getNumberD("write a: "),
                            user.getNumberD("write b: "),
                            user.getNumberD("write c: "),
                            user.getNumberD("write m: "),
                            user.getNumberD("write n: "));
                    correct=true;
                    break;
                default:
                    System.out.println("Try again or choose 0 to exit");
                    break;
            }
        }
        return trueFalse;
    }
    public CorrectStatement()
    {
        statementInfo=new String[9];
        statementInfo[0]="Целое число N является четным двузначным числом.";
        statementInfo[1]="Сумма двух первых цифр заданного четырехзначного числа равна сумме двух его последних цифр.";
        statementInfo[2]="Сумма цифр данного трехзначного числа N является четным числом.";
        statementInfo[3]="Точка с координатами (х, у) принадлежит части плоскости, лежащей между прямыми х= т, х= п (т<п).";
        statementInfo[4]="Квадрат заданного трехзначного числа равен кубу суммы цифр этого числа.";
        statementInfo[5]="Треугольник со сторонами а,b,с является равнобедренным. ";
        statementInfo[6]="Сумма каких-либо двух цифр заданного трехзначного натурального числа N равна третьей цифре. ";
        statementInfo[7]="Заданное число N является степенью числа а (показатель степени может находиться в диапазоне от 0 до 4).";
        statementInfo[8]="График функции у = ах2 + bх+ с проходит через заданную точку с координатами (m, п).";
        trueFalse=false;
        user = new Interaction();
    }
    public void statement1(int number)
    {
        if(number < 100 && number > 9 && number % 2 == 0){
            trueFalse=true;
        }
        else{
            trueFalse=false;
        }
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
    public void statement3(int number){
        if(number >99 && number<1000){
            int a = number /100;
            int b = number /10 - a*10;
            int c = number - a*100 - b*10;
            if((a+b+c)%2==0){
                trueFalse=true;
            }
            else{
                trueFalse=false;
            }
        }
    }
    public void statement4(Point a,int b,int c){
        if(a.getX()<c && a.getX()>b){
            trueFalse = true;
        }
        else{
            trueFalse = false;
        }
    }
    public void statement5(int number){
        int a = number/100;
        int b = number/10-a*10;
        int c = number - a*100 - b*10;
        if((number^2)<((a+b+c)^3)){
            trueFalse=true;
        }
        else {
            trueFalse=false;
        }
    }
    public void statement6(Triangle tr){
        if(tr.getSides()[0]==tr.getSides()[1] &&
                tr.getSides()[0]==tr.getSides()[2] &&
                tr.getSides()[1]==tr.getSides()[2] ){
            trueFalse=true;
        }
        else {
            trueFalse=false;
        }
    }
    public void statement7(int number){
        int a = number/100;
        int b = number/10-a*10;
        int c = number - a*100 - b*10;
        if(c==a+b && c==a+c && c==b+c){
            trueFalse=true;
        }
        else {
            trueFalse=false;
        }
    }
    public void statement8(int n, int a){
        if(n==1 && n==a && n==(a^2) && n==(a^3) && n==(a^4)){
            trueFalse=true;
        }
        else {
            trueFalse=false;
        }
    }
    public void statement9(double a, double b, double c, double m, double n){
        if(a*m*m + b*m + c == n){
            trueFalse=true;
        }
        else {
            trueFalse = false;
        }
    }
    public void statePrint()
    {
        int i=1;
        for (String st:statementInfo) {
            System.out.println(i+"."+st);
            i++;
        }
    }
}
