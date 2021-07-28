package by.training.task1.service;

import by.training.task1.view.Interaction;

public class Menu {
    private Interaction user;
    public Menu() {
        user=new Interaction();
    }
    public void menurun(){
        int choose = user.getNumberI("choose 1-5 task");
        switch(choose){
            case 1:
                System.out.println("Distance = " + CalculateDistance.betweenPoints(user.getPoint("create first point"),
                        user.getPoint("create second point")));
                break;
            case 2:
                CorrectStatement CS = new CorrectStatement();
                CS.statePrint();
                System.out.println("statement: " + CS.menuState());
                break;
            case 3:
                System.out.println("Time: " + TimeConverter.numToTime(user.getNumberI("write number: ")));
                break;
            case 4:
                System.out.println("Square = " + RectangleSquare.square(user.getNumberD("write width: ")));
                break;
            case 5:
                System.out.println("New time: " + ElapcedTime.calculate(user.getNumberI("write hours: "),
                        user.getNumberI("write minutes: "),
                        user.getNumberI("write seconds: "),
                        user.getNumberI("write p: "),
                        user.getNumberI("write q: "),
                        user.getNumberI("write r: ")));
                break;
            default:
                System.out.println("invalid number");
                break;
        }
    }
}
