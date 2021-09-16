package by.training.task5.service;

import by.training.task5.bean.Matrix;
import by.training.task5.dao.DaoException;
import by.training.task5.dao.DaoFactory;


public class MatrixCreator {
    private final int MINCOUNTARGS = 3;
    /**
     * File path which keeps matrix's data.
     */
    private String file;

    /**
     * Constructor.
     * @param file File path
     */
    public MatrixCreator(String file) {
        this.file = file;
    }
    /**
     * This method create matrix by using input data
     * to be keeped  in the file, and return reference to this Matrix class.
     * @return Matrix
     * @throws ServiceException Exception for service layer
     */
    public Matrix create() throws ServiceException {
        try {
            Parser parser = new Parser();
            int[] data = parser.parse(DaoFactory.getInstance()
                    .getReaderDao(file).read());
            if (data.length < MINCOUNTARGS) {
                throw new ServiceException("too little data");
            }
            if (data.length != 2 + (data[0] * data[1])) {
                throw new ServiceException("too much or too "
                        + "little matrix's values");
            }
            Matrix matrix = new Matrix(data[0], data[1]);
            for (int i = 0; i < matrix.getVertical(); i++) {
                for (int j = 0; j < matrix.getHorizontal(); j++) {
                    matrix.set(i, j, data[matrix.getVertical() * i + j + 2]);
                }
            }
            return matrix;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
