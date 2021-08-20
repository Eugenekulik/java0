package by.training.task3.service;

import by.training.task3.bean.MyArray;
import by.training.task3.dao.DaoException;
import by.training.task3.dao.DaoFactory;
import by.training.task3.service.ServiceException;

import java.lang.reflect.Array;
import java.util.Scanner;

public class GetArrayFromFile<T extends Comparable>{
    String filePath;
    public GetArrayFromFile(String filePath){
        this.filePath=filePath;
    }
    public MyArray create(Class<T> type) throws ServiceException {
        try {
            Scanner scanner = DaoFactory.getInstance().getScanner(filePath);
            int size= Integer.parseInt(scanner.next());
            MyArray<T> array = new MyArray<T>(type,size);
            for(int i=0;i<size;i++){
                if(type.getTypeName()=="java.lang.Integer") {
                    Integer temp = Integer.parseInt(scanner.next());
                    array.set(i, (T) temp);
                }
                else return null;
            }
            return array;

        }
        catch (DaoException e){
            throw new ServiceException(e);
        }
    }

}
