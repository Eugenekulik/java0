package by.training.task5.service;

import by.training.task5.bean.Matrix;

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
     */
    public boolean check() {
        if (matrix.getHorizontal() != matrix.getVertical()) {
            return false;
        }
        for (int i = 0; i < matrix.getVertical(); i++) {
            if (matrix.get(i, i) == 0) {
                return false;
            }
        }
        return true;
    }
}
