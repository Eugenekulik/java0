package by.training.task3.view;


/**
 * It's factory for view layer
 */

public class ViewFactory {
    private final static ViewFactory instance = new ViewFactory();
    private ViewFactory(){};
    private Reader reader = new Reader();
    private Messenger messenger =new Messenger();
    public static  ViewFactory getInstance(){
        return instance;
    }
    public Reader getReader(){
        return reader;
    }
    public Messenger getMessenger() {
        return messenger;
    }
}
