package by.training.task2.controller.command;

import by.training.task2.service.ServiceException;

public class MathTaskCommand implements Command{
    private Receiver receiver;
    public MathTaskCommand(Receiver receiver) {
        this.receiver = receiver;
    }
    public void execute() throws ServiceException {
        receiver.action(CommandType.MATHTASK);
    }
}
