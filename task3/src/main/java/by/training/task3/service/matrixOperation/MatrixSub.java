package by.training.task3.service.matrixOperation;

import by.training.task3.bean.IntegerMatrix;
import by.training.task3.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This class subtracts matrices
 */
public class MatrixSub {
    public MatrixSub(){};
    private static final Logger logger = LogManager.getLogger(MatrixSub.class);
    /**
     * This method substract IntegerMatrix's
     * @param a first matrix
     * @param b second matrix
     * @return IntegerMatrix
     * @throws ServiceException
     * @see IntegerMatrix
     */
    public IntegerMatrix result(IntegerMatrix a, IntegerMatrix b) throws ServiceException {
        logger.info("Matrix substract run");
        if(a.getHorizontalSize()!=b.getHorizontalSize()){
            throw new ServiceException("command.matrix.dif_horsize");
        }
        if(a.getVerticalSize()!=b.getVerticalSize()){
            throw new ServiceException("command.matrix.dif_versize");
        }
        IntegerMatrix c = new IntegerMatrix(a.getHorizontalSize(),a.getVerticalSize());
        Integer temp = 0;
        for(int i=0;i<a.getVerticalSize();i++){
            for(int j=0;j<a.getHorizontalSize();j++) {
                temp = a.getElement(i, j)-b.getElement(i,j);
                c.setElement(i, j, temp);
            }
        }
        logger.info("Martix substract completed");
        return c;
    }
}
