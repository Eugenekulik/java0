package by.training.task5.service;

import by.training.task5.bean.Matrix;
import by.training.task5.service.AtomicInteger.MatrixChangeAtomicInteger;
import by.training.task5.service.Phaser.MatrixChangeSet;

public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();

    public static ServiceFactory getInstance(){
        return instance;
    }
    public MatrixChange getMatrixChange(Matrix matrix, int[]values){
        return new MatrixChangeSet(matrix, values);
    }

}
