package by.training.task3.service;

import by.training.task3.bean.IntegerMatrix;

import java.util.ArrayList;

/**
 * This class create MyMatrix
 */
public class MatrixManager {
    private final static MatrixManager instance = new MatrixManager();
    public static  MatrixManager getInstance(){
        return instance;
    }

    /**
     * This method create and return IntegerMatrix
     * @param temp ArrayList<Integer> with arguments for the matrix
     * @param n vertical size
     * @param m horizontal size
     * @return
     * @throws ServiceException
     */
    public IntegerMatrix createIntegerMatrix(ArrayList<Integer> temp,int n,int m) throws ServiceException {
        if(temp.size()<m*n) {
            throw new ServiceException("can't create matrix, too much or too little argument");
        }
        return new IntegerMatrix(temp,n,m);
    }
}
