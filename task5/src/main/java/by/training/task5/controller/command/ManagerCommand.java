package by.training.task5.controller.command;

import by.training.task5.service.ServiceException;

/**
 * ManagerCommand class to be included in Command pattern.
 * This class invoke command which be given.
 */
public class ManagerCommand {
    /**
     * Command which will be invoked.
     */
    private Command command;
    /**
     * Constructor which takes the command.
     * @param command
     */
    public ManagerCommand(Command command) {
        this.command = command;
    }
    /**
     * This method invoke the command.
     * @throws ServiceException
     */
    public void invokeCommand() throws ServiceException {
        command.execute();
    }
}
