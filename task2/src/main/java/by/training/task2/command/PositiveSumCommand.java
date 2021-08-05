package by.training.task2.command;

public class PositiveSumCommand implements Command{
    private Receiver receiver;
    public PositiveSumCommand(Receiver receiver) {
        this.receiver = receiver;
    }
    public void execute() {
        receiver.action(CommandType.POSSUM);
    }
}
