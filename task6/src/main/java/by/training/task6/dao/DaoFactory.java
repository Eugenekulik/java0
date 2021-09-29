package by.training.task6.dao;

public class DaoFactory {
    private static final DaoFactory instance = new DaoFactory();
    public static DaoFactory getInstance() {
        return instance;
    }

    public FileReader getFileReader(String file) throws DaoException {
        return new FileReader(file);
    }
}
