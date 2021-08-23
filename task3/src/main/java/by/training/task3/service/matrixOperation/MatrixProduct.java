package by.training.task3.service.matrixOperation;

import by.training.task3.bean.IntegerMatrix;
import by.training.task3.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class multiply two matrix
 */
public class MatrixProduct {
    public MatrixProduct(){}
    private static final Logger logger = LogManager.getLogger(MatrixProduct.class);
    /**
     * This method multiply two IntegerMatrix
     * @param a first matrix
     * @param b second matrix
     * @return IntegerMatrix
     * @throws ServiceException
     * @see IntegerMatrix
     */
    public IntegerMatrix result(IntegerMatrix a, IntegerMatrix b) throws ServiceException {
        logger.info("Matrix product run");
        if(a.getHorizontalSize()==b.getVerticalSize()){
            int temp = 0;
            IntegerMatrix c = new IntegerMatrix(a.getVerticalSize(),b.getHorizontalSize());
            for(int i=0;i<c.getVerticalSize();i++){
                for(int j=0;j<c.getHorizontalSize();j++){
                    for(int k=0;k<a.getHorizontalSize();k++){
                        temp += b.getElement(k,i)*a.getElement(j,k);
                    }
                    c.setElement(i,j,temp);
                    temp = 0;
                }
            }
            return c;
        }
        else if(a.getVerticalSize()==b.getHorizontalSize()){
            int temp = 0;
            IntegerMatrix c = new IntegerMatrix(a.getHorizontalSize(),b.getVerticalSize());
            for(int i=0;i<c.getVerticalSize();i++){
                for(int j=0;j<c.getHorizontalSize();j++){
                    for(int k=0;k<a.getHorizontalSize();k++){
                        temp += b.getElement(i,k)*a.getElement(k,j);
                    }
                    c.setElement(i,j,temp);
                    temp = 0;
                }
            }
            logger.info("Matrix product completed");
            return c;
        }
        else{
            throw new ServiceException("command.matrix.cant_multiply");
        }

    }
}
