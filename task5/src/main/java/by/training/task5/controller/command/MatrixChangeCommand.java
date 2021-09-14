package by.training.task5.controller.command;

import by.training.task5.bean.Matrix;
import by.training.task5.controller.Runner;
import by.training.task5.service.MatrixChange;
import by.training.task5.service.ServiceException;
import by.training.task5.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class MatrixChangeCommand implements Command{
    private static final Logger logger = LogManager.getLogger(Runner.class);
    private int countThread;
    private Matrix matrix;
    private int[] threadinfo;
    public MatrixChangeCommand(Matrix matrix, int []threadinfo){
        this.countThread = countThread;
        this.matrix = matrix;
        this.threadinfo = threadinfo;
    }
    public void execute() throws ServiceException {
        MatrixChange matrixChange = ServiceFactory.getInstance()
                .getMatrixChange(matrix,threadinfo);
        matrixChange.change();
    }
}
