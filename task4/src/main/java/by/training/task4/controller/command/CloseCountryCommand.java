package by.training.task4.controller.command;

import by.training.task4.bean.CommandData;

public class CloseCountryCommand implements Command{
    CommandData commandData;
    public CloseCountryCommand(CommandData commandData){this.commandData = commandData;}
    public void execute(){

    }
}
