package by.training.task3.service.matrixOperation;

import by.training.task3.bean.IntegerMatrix;
import by.training.task3.service.ServiceException;

import java.util.ArrayList;

public class MatrixManager {
    private final static MatrixManager instance = new MatrixManager();
    public static  MatrixManager getInstance(){
        return instance;
    }
    public IntegerMatrix createIntegerMatrix(ArrayList<Integer> temp,int n,int m) throws ServiceException {
        if(temp.size()<m*n) {
            throw new ServiceException("can't create matrix, too much or too little argument");
        }
        return new IntegerMatrix(temp,n,m);
    }
}
