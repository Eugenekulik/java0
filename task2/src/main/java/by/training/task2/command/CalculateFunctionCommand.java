package by.training.task2.command;

public class CalculateFunctionCommand implements Command{
    private Receiver receiver;
    public CalculateFunctionCommand(Receiver receiver) {
        this.receiver = receiver;
    }
    public void execute() {
        receiver.action(CommandType.CALCFUNC);
    }
}
