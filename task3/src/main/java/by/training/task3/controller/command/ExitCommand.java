package by.training.task3.controller.command;

import by.training.task3.bean.RuntimeInformation;

public class ExitCommand implements Command{
    public ExitCommand(){};
    @Override
    public void execute(){
        RuntimeInformation.setIsWork(false);
    }
}
