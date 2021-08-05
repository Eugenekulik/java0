package by.training.task2.command;

public class ProductSequenceCommand implements Command{
    private Receiver receiver;
    public ProductSequenceCommand(Receiver receiver) {
        this.receiver = receiver;
    }
    public void execute() {
        receiver.action(CommandType.PRODSEQ);
    }
}
