package by.training.task5.dao;

public class DaoFactory {
    private static final DaoFactory instance = new DaoFactory();

    public static DaoFactory getInstance() {
        return instance;
    }

    public ReaderDao getReaderDao(String file) throws DaoException {
        return new ReaderDao(file);
    }
}
