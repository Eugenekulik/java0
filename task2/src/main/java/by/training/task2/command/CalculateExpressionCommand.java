package by.training.task2.command;

public class CalculateExpressionCommand implements Command{
    private Receiver receiver;
    public CalculateExpressionCommand(Receiver receiver) {
        this.receiver = receiver;
    }
    public void execute() {
        receiver.action(CommandType.CALCEXPR);
    }
}
