package by.training.task5.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReaderDao {
    private Scanner scanner;
    public ReaderDao(String file) throws DaoException {
        try{
            scanner  = new Scanner(new FileInputStream(new File(file)));
        }
        catch (FileNotFoundException e){
            throw new DaoException(e);
        }
    }
    public String read(){
        StringBuffer stringBuffer = new StringBuffer();
        while(scanner.hasNext()) {
            stringBuffer.append(scanner.nextLine()+" ");
        }
        return stringBuffer.toString();
    }
}
