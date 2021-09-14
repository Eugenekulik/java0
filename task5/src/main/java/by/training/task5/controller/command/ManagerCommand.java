package by.training.task5.controller.command;

import by.training.task5.service.ServiceException;

public class ManagerCommand {
    private Command command;
    public ManagerCommand(Command command){
        this.command = command;
    }
    public void invokeCommand() throws ServiceException {
        command.execute();
    }
}
