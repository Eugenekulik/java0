package by.training.task6.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This class give opportunity read information from file source.
 */
public class FileReader {
    /**
     * Class Scanner which give oppotunite read filestream.
     */
    private Scanner scanner;
    /**
     * Constructor which takes file path.
     *
     * @param file file path with data
     * @throws DaoException Exception for Dao layer
     */
    public FileReader(String file) throws DaoException {
        try {
            scanner = new Scanner(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            throw new DaoException(e);
        }
    }
    /**
     * This method read all information from file
     * and then return String with data.
     *
     * @return String with data
     */
    public String readAll() {
        StringBuilder stringBuilder = new StringBuilder();
        while (scanner.hasNext()) {
            stringBuilder.append(scanner.nextLine() + " ");
        }
        return stringBuilder.toString();
    }
    public String readNextLine() {
        if(scanner.hasNext()) {
            return scanner.nextLine();
        }
        return null;
    }
    public String readNext(){
        if(scanner.hasNext()) {
            return scanner.next();
        }
        return null;
    }
}
