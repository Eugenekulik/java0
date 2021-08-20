package by.training.task3.controller.command;

import by.training.task3.service.ServiceException;

public class ManagerCommand {
    private Command mCommand;
    public ManagerCommand(Command command) {
        mCommand = command;
    }
    public void invokeCommand() throws ServiceException {
        mCommand.execute();
    }
}
