package by.training.task3.service;

import by.training.task3.service.matrixOperation.MatrixProduct;
import by.training.task3.service.matrixOperation.MatrixSub;
import by.training.task3.service.matrixOperation.MatrixSum;

public class ServiceFactory {
    private final static ServiceFactory instance = new ServiceFactory();
    private MatrixSum matrixSum=new MatrixSum();
    private MatrixProduct matrixProduct = new MatrixProduct();
    private MatrixSub matrixSub = new MatrixSub();
    public static ServiceFactory getInstance(){
        return instance;
    }
    public MatrixSum getMatrixSum(){
        return matrixSum;
    }
    public MatrixProduct getMatrixProduct(){
        return matrixProduct;
    }
    public MatrixSub getMatrixSub(){
        return matrixSub;
    }
}
