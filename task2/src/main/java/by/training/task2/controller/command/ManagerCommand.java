package by.training.task2.controller.command;

import by.training.task2.service.ServiceException;

public class ManagerCommand {


    private Command mCommand;
    public ManagerCommand(Command command) {
        mCommand = command;
    }
    public void invokeCommand() throws ServiceException {
        mCommand.execute();
    }
}
