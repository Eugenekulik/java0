package by.training.task2.command;

import by.training.task2.service.CalculateFunctionTable;

public class CalculateFunctionTableCommand implements Command{
    private Receiver receiver;
    public CalculateFunctionTableCommand(Receiver receiver) {
        this.receiver = receiver;
    }
    public void execute() {
        receiver.action(CommandType.CALCFUNCTABLE);
    }
}
