package by.training.task5.service;

import by.training.task5.bean.Matrix;
import by.training.task5.bean.MatrixException;

/**
 * This class checks the matrix for some
 * conditions necessary for the problem.
 */
public class MatrixCheck {
    /**
     * Matrix reference which will be check.
     */
    private Matrix matrix;
    /**
     * Constructor.
     * @param matrix The Matrix which will be changed.
     */
    public MatrixCheck(Matrix matrix) {
        this.matrix = matrix;
    }
    /**
     * This method check the Matrix.
     * @return true if matrix satisfy conditions
     * @throws ServiceException Exception for service layer
     */
    public boolean check() throws ServiceException {
        if (matrix.getHorizontal() != matrix.getVertical()) {
            return false;
        }
        try {
            for (int i = 0; i < matrix.getVertical(); i++) {
                if (matrix.get(i, i) == 0) {
                    return false;
                }
            }
        }catch (MatrixException e){
            throw new ServiceException(e);
        }
        return true;
    }
}
