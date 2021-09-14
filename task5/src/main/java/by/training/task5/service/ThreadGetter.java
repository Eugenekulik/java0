package by.training.task5.service;

import by.training.task5.dao.DaoException;
import by.training.task5.dao.DaoFactory;

import java.util.Arrays;

public class ThreadGetter {
    private String file;
    public ThreadGetter(String file){
        this.file = file;
    }
    public int[] get() throws ServiceException  {
        try{
            String []data = DaoFactory.getInstance().getReaderDao(file).read().split(" ");
            int[] array = Arrays.stream(data).mapToInt(Integer::parseInt).toArray();
            return array;
        }
        catch (NumberFormatException|DaoException e){
            throw new ServiceException(e);
        }
    }
}
