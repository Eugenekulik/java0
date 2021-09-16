package by.training.task5.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This class give oppotunity read information from file source.
 */
public class ReaderDao {
    /**
     * Class Scanner which give oppotunite read filestream.
     */
    private Scanner scanner;
    /**
     * Constructor which takes file path.
     * @param file file path with data
     * @throws DaoException Exception for Dao layer
     */
    public ReaderDao(String file) throws DaoException {
        try {
            scanner  = new Scanner(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            throw new DaoException(e);
        }
    }

    /**
     * This method read all information from file
     * and then return String with data.
     * @return String with data
     */
    public String read() {
        StringBuilder stringBuilder = new StringBuilder();
        while (scanner.hasNext()) {
            stringBuilder.append(scanner.nextLine() + " ");
        }
        return stringBuilder.toString();
    }
}
