package by.training.task5.service;

import by.training.task5.bean.Matrix;
import by.training.task5.service.set.MatrixChangeSet;

import java.util.Random;

public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();
    private final Random random = new Random();

    public static ServiceFactory getInstance() {
        return instance;
    }

    public MatrixChange getMatrixChange(Matrix matrix, int[] values) {
        return new MatrixChangeSet(matrix, values);
    }

    public Random getRandom() {
        return random;
    }
}
