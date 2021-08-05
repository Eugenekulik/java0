package by.training.task2.command;

public class RedistributionCommand implements Command{
    private Receiver receiver;
    public RedistributionCommand(Receiver receiver) {
        this.receiver = receiver;
    }
    public void execute() {
        receiver.action(CommandType.REDISTR);
    }
}
