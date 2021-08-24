package by.training.task4.controller.command;

import by.training.task4.bean.RunTimeInfo;

public class ExitCommand implements Command{
    public void execute(){
        RunTimeInfo.setWork(false);
    }
}
