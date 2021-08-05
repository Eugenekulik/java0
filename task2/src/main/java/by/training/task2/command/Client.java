package by.training.task2.command;

import by.training.task2.service.CalculateExpression;

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
                command = new CalculateFunctionCommand(cReceiver);
                break;
            case MATHTASK:
                command = new CalculateFunctionCommand(cReceiver);
                break;
            case POSSUM:
                command = new CalculateFunctionCommand(cReceiver);
                break;
            case PRODSEQ:
                command = new CalculateFunctionCommand(cReceiver);
                break;
            case REDISTR:
                command = new CalculateFunctionCommand(cReceiver);
                break;
        }
        return command;
    }
}
