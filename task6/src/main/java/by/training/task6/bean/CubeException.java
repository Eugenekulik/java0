package by.training.task6.bean;

public class CubeException extends Exception {
    public CubeException() {
        super();
    }

    public CubeException(String message) {
        super(message);
    }

    public CubeException(String message, Throwable cause) {
        super(message, cause);
    }

    public CubeException(Throwable cause) {
        super(cause);
    }
}
