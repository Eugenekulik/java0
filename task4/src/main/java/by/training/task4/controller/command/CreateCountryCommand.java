package by.training.task4.controller.command;

import by.training.task4.bean.CommandData;

public class CreateCountryCommand implements Command{
    CommandData commandData;
    public CreateCountryCommand(CommandData commandData){this.commandData = commandData;}
    public void execute(){
        
    }
}
