package by.training.task2.controller.command;

import by.training.task2.service.ServiceException;

public interface Command {
    void execute() throws ServiceException;
}
