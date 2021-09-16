package by.training.task5.service;

/**
 * This class extends Exception and serves for
 * union all exception in service layer.
 */
public class ServiceException extends Exception {
    /**
     * Constructor without parameters.
     */
    public ServiceException() {
        super();
    }
    /**
     * Constructor which takes String parameter
     * which keep information about error.
     * @param message String keep error information
     */
    public ServiceException(String message) {
        super(message);
    }
    /**
     * Constructor which takes Throwable class which keep
     * addictional information about error.
     * @param cause Throwable
     */
    public ServiceException(Throwable cause) {
        super(cause);
    }
    /**
     * Constructor which takes String and Throwable.
     * @param message String with error information
     * @param cause Throwable with additional information about error
     */
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
