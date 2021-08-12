package by.training.task2.controller.command;

import by.training.task2.service.ServiceException;

public class ProductSequenceCommand implements Command{
    private Receiver receiver;
    public ProductSequenceCommand(Receiver receiver) {
        this.receiver = receiver;
    }
    public void execute() throws ServiceException {
        receiver.action(CommandType.PRODSEQ);
    }
}
