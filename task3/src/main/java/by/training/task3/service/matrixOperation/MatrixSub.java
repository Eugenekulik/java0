package by.training.task3.service.matrixOperation;

import by.training.task3.bean.IntegerMatrix;
import by.training.task3.service.ServiceException;

/**
 * This class subtracts matrices
 */
public class MatrixSub {
    public MatrixSub(){};

    /**
     * This method substract IntegerMatrix's
     * @param a first matrix
     * @param b second matrix
     * @return IntegerMatrix
     * @throws ServiceException
     * @see IntegerMatrix
     */
    public IntegerMatrix result(IntegerMatrix a, IntegerMatrix b) throws ServiceException {
        if(a.getHorizontalSize()!=b.getHorizontalSize()){
            throw new ServiceException("different horizontal size");
        }
        if(a.getVerticalSize()!=b.getVerticalSize()){
            throw new ServiceException("different vertical size");
        }
        IntegerMatrix c = new IntegerMatrix(a.getHorizontalSize(),a.getVerticalSize());
        Integer temp = 0;
        for(int i=0;i<a.getVerticalSize();i++){
            for(int j=0;j<a.getHorizontalSize();j++) {
                temp = a.getElement(i, j)-a.getElement(i,j);
                c.setElement(i, j, temp);
            }
        }
        return c;
    }
}
