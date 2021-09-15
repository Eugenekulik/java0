package by.training.task5.controller.command;

import by.training.task5.bean.Matrix;


public class Client {
    private Matrix matrix;
    private int[] threadInfo;
    public Client(Matrix matrix,int[] threadInfo){
        this.matrix = matrix;
        this.threadInfo = threadInfo;
    }
    public Command initCommand(CommandType cmd){
        Command command = null;
        switch (cmd){
            case EXIT:
                command = new ExitCommand();
                break;
            case MATRIXCHANGE:
                command = new MatrixChangeCommand(matrix,threadInfo);
                break;
            default:
                break;
        }
        return command;
    }
}
