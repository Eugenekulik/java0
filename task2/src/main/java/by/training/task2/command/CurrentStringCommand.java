package by.training.task2.command;

public class CurrentStringCommand implements Command{
    private Receiver receiver;
    public CurrentStringCommand(Receiver receiver) {
        this.receiver = receiver;
    }
    public void execute() {
        receiver.action(CommandType.CURRSTR);
    }
}
