package by.training.task6.controller.command;

import by.training.task6.service.ServiceException;

public interface Command {
    public void execute() throws ServiceException;
}
