package by.training.task4.controller.command;

import by.training.task4.bean.CommandData;
import by.training.task4.bean.CommandType;

public class Client {
    CommandData commandData;
    Command command = null;
    public Client(CommandData commandData){
        this.commandData = commandData;
    }
    public Command initCommand(CommandType cmd){
        switch (cmd){
            case EXIT:
                command = new ExitCommand();
                break;
            case OPENCOUNTRY:
                command = new OpenCountryCommand(commandData);
                break;
            case CLOSECOUNTRY:
                command = new CloseCountryCommand(commandData);
                break;
            case CREATECOUNTRY:
                command = new CreateCountryCommand(commandData);
                break;
        }
        return command;
    }
}
