package by.training.task5.controller.command;

import by.training.task5.bean.Matrix;
import by.training.task5.service.MatrixChange;
import by.training.task5.service.ServiceException;
import by.training.task5.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class MatrixChangeCommand implements Command{
    public static final Logger logger = LogManager.getLogger(MatrixChangeCommand.class);
    private Matrix matrix;
    private int[] threadinfo;
    public MatrixChangeCommand(Matrix matrix, int []threadinfo){
        this.matrix = matrix;
        this.threadinfo = threadinfo;
    }
    public void execute() throws ServiceException {
        logger.info("Matrix change command start");
        MatrixChange matrixChange = ServiceFactory.getInstance()
                .getMatrixChange(matrix,threadinfo);
        matrixChange.change();
        logger.info("Matrix change command start");
    }
}
