package by.training.task1.view;

import by.training.task1.service.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Menu {
    private Interaction user;
    private static final Logger logger = LogManager.getLogger(Menu.class);
    public Menu() {
        user=new Interaction();

    }
    public void menurun(){
        int choose = user.getNumberI("choose 1-5 task");
        switch(choose){
            case 1:
                logger.info("Task1 start");
                System.out.println("Distance = " + CalculateDistance.betweenPoints(user.getPoint("create first point"),
                        user.getPoint("create second point")));
                logger.info("Task1 complete");
                break;
            case 2:
                logger.info("Task2 start");
                CorrectStatement CS = new CorrectStatement();
                CS.statePrint();
                System.out.println("statement: " + CS.menuState());
                logger.info("Task2 complete");
                break;
            case 3:
                logger.info("Task3 start");
                System.out.println("Time: " + TimeConverter.numToTime(user.getNumberI("write number: ")));
                logger.info("Task3 complete");
                break;
            case 4:
                logger.info("Task4 start");
                System.out.println("Square = " + RectangleSquare.square(user.getNumberD("write width: ")));
                logger.info("Task4 complete");
                break;
            case 5:
                logger.info("Task5 start");
                System.out.println("New time: " + ElapcedTime.calculate(user.getNumberI("write hours: "),
                        user.getNumberI("write minutes: "),
                        user.getNumberI("write seconds: "),
                        user.getNumberI("write p: "),
                        user.getNumberI("write q: "),
                        user.getNumberI("write r: ")));
                logger.info("Task5 complete");
                break;
            default:
                logger.warn("invalid number");
                System.out.println("invalid number");
                break;
        }
    }
}
