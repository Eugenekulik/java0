package by.training.task3.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 * Factory for Dao layer
 */
public class DaoFactory {
    private final static DaoFactory instance = new DaoFactory();
    public Scanner getScanner(String filePath) throws DaoException {
        File file = new File(filePath);
        try {
            FileReader fileReader = new FileReader(file);
            Scanner scanner = new Scanner(fileReader);
            return scanner;
        }
        catch(FileNotFoundException e){
            throw new DaoException("File not found",e);
        }
    }

    public static DaoFactory getInstance(){
        return instance;
    }
}
