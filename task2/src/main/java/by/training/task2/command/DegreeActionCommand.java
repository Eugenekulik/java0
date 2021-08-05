package by.training.task2.command;

public class DegreeActionCommand implements Command{
    private Receiver receiver;
    public DegreeActionCommand(Receiver receiver) {
        this.receiver = receiver;
    }
    public void execute() {
        receiver.action(CommandType.DEGREEACT);
    }
}
