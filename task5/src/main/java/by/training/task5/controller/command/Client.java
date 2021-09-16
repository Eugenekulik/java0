package by.training.task5.controller.command;

import by.training.task5.bean.Matrix;

/**
 * This class to be included in Command pattern
 * and create command by command type and output data.
 */
public class Client {
    /**
     * Matrix reference for command's manipulations.
     */
    private Matrix matrix;
    /**
     * This array keep the information about threads.
     */
    private int[] threadInfo;

    /**
     * Constructor.
     * @param matrix matrix reference for change.
     * @param threadInfo threads's info
     */
    public Client(Matrix matrix, int[] threadInfo) {
        this.matrix = matrix;
        this.threadInfo = threadInfo;
    }

    /**
     * This method initialize command by command type.
     * @param cmd value of CommandType enumeration
     * @return Command
     */
    public Command initCommand(CommandType cmd) {
        Command command = null;
        switch (cmd) {
            case EXIT:
                command = new ExitCommand();
                break;
            case MATRIXCHANGE:
                command = new MatrixChangeCommand(matrix, threadInfo);
                break;
            default:
                break;
        }
        return command;
    }
}
