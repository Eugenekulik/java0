package by.training.task3.controller.command;

import by.training.task3.bean.CommandData;

public class Client {
    private CommandData cCommandData;
    public Client(CommandData commandData) {
        cCommandData = commandData;
    }
    public Command initCommand(CommandType cmd) {
        Command command = null;
        switch(cmd) {
            case SORT:
                command = new SortCommand(cCommandData);
                break;
            case MATRIXSUM:
                command = new MatrixSumCommand(cCommandData);
                break;
            case MATRIXSUB:
                command = new MatrixSubCommand(cCommandData);
                break;
            case MATRIXPRODUCT:
                command = new MatrixProductCommand(cCommandData);
                break;
            case EXIT:
                command = new ExitCommand();
                break;
        }
        return command;
    }
}
