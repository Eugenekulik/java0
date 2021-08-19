package by.training.task3.view;

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
