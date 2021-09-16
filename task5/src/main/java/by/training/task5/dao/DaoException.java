package by.training.task5.dao;

/**
 * Class DaoException extends Exception and realize functional for dao layer.
 * It catch all exception which throwed at dao layer.
 */
public class DaoException extends Exception {
    /**
     * Constructor without parameters.
     */
    public DaoException() {
        super();
    }

    /**
     * Constructor with Throwable parameter which
     * keep the additional information.
     * @param e Throwable
     */
    public DaoException(Throwable e) {
        super(e);
    }
    /**
     * Constructor with String parameter which keep the message error.
     * @param message String with message
     */
    public DaoException(String message) {
        super(message);
    }

    /**
     * Constructor with String and Throwable parameters
     * which keep the message error and additional information.
     * @param message String with message
     * @param e Throwable
     */
    public DaoException(String message, Throwable e) {
        super(message, e);
    }
}
