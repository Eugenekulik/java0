package by.training.task5.controller.command;

import java.util.Optional;

public class Client {
    Optional<?> data;
    public Client(Optional<?> data){
        this.data = data;
    }
    public Command initCommand(CommandType cmd){
        Command command = null;
        switch (cmd){
            case EXIT:
                command = new ExitCommand();
            default:
        }
        return command;
    }
}
