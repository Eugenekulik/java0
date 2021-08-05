package by.training.task2.command;

public class MathTaskCommand implements Command{
    private Receiver receiver;
    public MathTaskCommand(Receiver receiver) {
        this.receiver = receiver;
    }
    public void execute() {
        receiver.action(CommandType.MATHTASK);
    }
}
