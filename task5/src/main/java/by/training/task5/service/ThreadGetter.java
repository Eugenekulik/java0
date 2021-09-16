package by.training.task5.service;

import by.training.task5.dao.DaoException;
import by.training.task5.dao.DaoFactory;

import java.util.Arrays;

/**
 * This class read information about threads.
 */
public class ThreadGetter {
    /**
     * File path keeps threads data.
     */
    private String file;
    /**
     * Constructor.
     * @param file file path with information
     */
    public ThreadGetter(String file) {
        this.file = file;
    }
    /**
     * This method return array of int.
     * First and second number are range of count treads.
     * Then numbers for each thread.
     * @return array int
     * @throws ServiceException Exception for service layer
     */
    public int[] get() throws ServiceException  {
        try {
            String[] data = DaoFactory.getInstance()
                    .getReaderDao(file).read().split(" ");
            return Arrays.stream(data).mapToInt(Integer::parseInt).toArray();
        } catch (NumberFormatException | DaoException e) {
            throw new ServiceException(e);
        }
    }
}
