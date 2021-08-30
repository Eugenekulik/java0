package by.training.task3.service;

import by.training.task3.bean.IntegerMatrix;

import by.training.task3.dao.DaoException;
import by.training.task3.dao.DaoFactory;

import java.util.ArrayList;
import java.util.Scanner;

public class GetMatrixFromFile<T extends Number> {
    String filePath;
    public GetMatrixFromFile(String filePath){
        this.filePath = filePath;
    }
    public IntegerMatrix createIntegerMatrix() throws ServiceException {
        try {
            Scanner scanner = DaoFactory.getInstance().getScanner(filePath);
            int n = Integer.parseInt(scanner.next());
            int m = Integer.parseInt(scanner.next());
            ArrayList<Integer> array = new ArrayList<>();
            for(int i=0;i<n*m;i++){
                array.add(Integer.parseInt(scanner.next()));
            }
            return new IntegerMatrix(array,n,m);
        }
        catch (DaoException e){
            throw new ServiceException(e);
        }
    }
}
