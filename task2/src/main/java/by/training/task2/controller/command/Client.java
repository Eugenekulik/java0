package by.training.task2.controller.command;

public class Client {
    private Receiver cReceiver;
    public Client(Receiver receiver) {
        cReceiver = receiver;
    }
    public Command initCommand(CommandType cmd) {
        Command command = null;
        switch(cmd) {
            case CALCEXPR:
                command=new CalculateExpressionCommand(cReceiver);
                break;
            case CALCFUNC:
                command = new CalculateFunctionCommand(cReceiver);
                break;
            case CALCFUNCSEGM:
                command = new CalculateFunctionSegmentCommand(cReceiver);
                break;
            case CALCFUNCTABLE:
                command = new CalculateFunctionTableCommand(cReceiver);
                break;
            case CURRSTR:
                command = new CurrentStringCommand(cReceiver);
                break;
            case DEGREEACT:
                command = new DegreeActionCommand(cReceiver);
                break;
            case MATHTASK:
                command = new MathTaskCommand(cReceiver);
                break;
            case POSSUM:
                command = new PositiveSumCommand(cReceiver);
                break;
            case PRODSEQ:
                command = new ProductSequenceCommand(cReceiver);
                break;
            case REDISTR:
                command = new RedistributionCommand(cReceiver);
                break;
        }
        return command;
    }
}
