package by.training.task4.controller.command;

import by.training.task4.service.ServiceException;

public interface Command {
    public void execute() throws ServiceException;
}
