package by.training.task5.controller.command;

import by.training.task5.service.ServiceException;

/**\
 * Interface Command pattern command.
 */
public interface Command {
    /**
     * Method execute command, which realize in the
     * class implements this interface.
     * @throws ServiceException
     */
    void execute() throws ServiceException;
}
