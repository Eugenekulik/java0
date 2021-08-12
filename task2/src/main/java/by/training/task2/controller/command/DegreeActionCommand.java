package by.training.task2.controller.command;

import by.training.task2.service.ServiceException;

public class DegreeActionCommand implements Command{
    private Receiver receiver;
    public DegreeActionCommand(Receiver receiver) {
        this.receiver = receiver;
    }
    public void execute() throws ServiceException {
        receiver.action(CommandType.DEGREEACT);
    }
}
