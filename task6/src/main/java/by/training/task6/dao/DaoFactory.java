package by.training.task6.dao;

import java.io.FileNotFoundException;

public class DaoFactory {
    private static final DaoFactory instance = new DaoFactory();

    public DaoFactory getInstance(){
        return instance;
    }

    public DaoReader getReader(String file)throws DaoException{
        try {
            return new DaoReader(file);
        }
        catch (FileNotFoundException e){
            throw new DaoException(e);
        }
    }

}
