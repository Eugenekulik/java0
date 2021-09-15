package by.training.task5.dao;

/**
 * Class realize pattern Factory for dao layer.
 */
public class DaoFactory {
    /**
     * Instance of singleton DaoFactory.
     */
    private static final DaoFactory INSTANCE = new DaoFactory();

    /**
     * Method return single instance of the DaoFactory.
     * @return DaoFactory
     */
    public static DaoFactory getInstance() {
        return INSTANCE;
    }

    /**
     * Return new instance of the ReaderDao.
     * @param file filepath for read
     * @return ReaderDao
     * @throws DaoException
     */
    public ReaderDao getReaderDao(String file) throws DaoException {
        return new ReaderDao(file);
    }
}
