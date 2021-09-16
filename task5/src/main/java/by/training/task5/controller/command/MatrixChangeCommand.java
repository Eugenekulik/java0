package by.training.task5.controller.command;

import by.training.task5.bean.Matrix;
import by.training.task5.service.MatrixChange;
import by.training.task5.service.ServiceException;
import by.training.task5.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This class implements interface Command and realize command
 * which call the Service class which change matrix.
 */
public class MatrixChangeCommand implements Command {
    /**
     * Logger.
     */
    public static final Logger LOGGER =
            LogManager.getLogger(MatrixChangeCommand.class);
    /**
     * Matrix reference which will be changed.
     */
    private Matrix matrix;
    /**
     * This array keep information about threads.
     */
    private int[] threadinfo;

    /**
     * Constructor.
     * @param matrix Matrix class which be changed
     * @param threadinfo int[] information about threads
     */
    public MatrixChangeCommand(Matrix matrix, int[] threadinfo) {
        this.matrix = matrix;
        this.threadinfo = threadinfo;
    }

    /**
     * Main method which execute command.
     * @throws ServiceException Exception for service layer
     */
    public void execute() throws ServiceException {
        LOGGER.info("Matrix change command start");
        MatrixChange matrixChange = ServiceFactory.getInstance()
                .getMatrixChange(matrix, threadinfo);
        matrixChange.change();
        LOGGER.info("Matrix change command start");
    }
}
