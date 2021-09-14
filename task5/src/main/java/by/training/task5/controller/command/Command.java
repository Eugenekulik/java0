package by.training.task5.controller.command;

import by.training.task5.service.ServiceException;

public interface Command {
    void execute() throws ServiceException;
}
