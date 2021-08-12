package by.training.task2.controller.command;

import by.training.task2.service.ServiceException;

public class CalculateFunctionTableCommand implements Command{
    private Receiver receiver;
    public CalculateFunctionTableCommand(Receiver receiver) {
        this.receiver = receiver;
    }
    public void execute() throws ServiceException {
        receiver.action(CommandType.CALCFUNCTABLE);
    }
}
