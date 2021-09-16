package by.training.task5.service;

import by.training.task5.bean.Matrix;
import by.training.task5.service.set.MatrixChangeSet;

import java.util.Random;

/**
 * This class is Singleton with services.
 */
public class ServiceFactory {
    /**
     * Single Instance.
     */
    private static final ServiceFactory INSTANCE = new ServiceFactory();
    /**
     * Random class for generation random numners.
     */
    private final Random random = new Random();
    /**
     * Getter for ServiceFactory.
     * @return single instance of ServiceFactory
     */
    public static ServiceFactory getInstance() {
        return INSTANCE;
    }
    /**
     * MatrixChanhe getter which return MatrixChange implementation.
     * @param matrix Matrix reference which will be changed.
     * @param values The value which will be written to the matrix
     * @return MatrixChange
     */
    public MatrixChange getMatrixChange(Matrix matrix, int[] values) {
        return new MatrixChangeSet(matrix, values);
    }
    /**
     * Getter for Random class.
     * @return Random
     */
    public Random getRandom() {
        return random;
    }
}
