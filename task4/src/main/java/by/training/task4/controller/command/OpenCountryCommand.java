package by.training.task4.controller.command;

import by.training.task4.bean.CommandData;

public class OpenCountryCommand implements Command{
    CommandData commandData;
    public OpenCountryCommand(CommandData commandData){this.commandData=commandData;}
    public void execute(){

    }
}
