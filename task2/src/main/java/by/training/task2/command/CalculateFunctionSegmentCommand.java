package by.training.task2.command;

import by.training.task2.service.CalculateFunctionSegment;

public class CalculateFunctionSegmentCommand implements Command{
    private Receiver receiver;
    public CalculateFunctionSegmentCommand(Receiver receiver) {
        this.receiver = receiver;
    }
    public void execute() {
        receiver.action(CommandType.CALCFUNCSEGM);
    }
}
