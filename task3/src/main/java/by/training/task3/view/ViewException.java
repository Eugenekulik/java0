package by.training.task3.view;

/**
 * This class is exception for view layer
 */
public class ViewException extends Exception{
    public ViewException(){};
    public ViewException(String msg){
        super(msg);
    }
    public ViewException(Throwable exception){
        super(exception);
    }
    public ViewException(String msg, Throwable exception){
        super(msg,exception);
    }
}
