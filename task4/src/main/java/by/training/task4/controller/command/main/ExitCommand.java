package by.training.task4.controller.command.main;

import by.training.task4.bean.ProgramData;
import by.training.task4.controller.command.Command;

public class ExitCommand implements Command {
    public void execute(){
        ProgramData.setWork(false);
    }
}
