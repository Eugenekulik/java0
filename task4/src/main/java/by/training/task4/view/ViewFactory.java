package by.training.task4.view;

public class ViewFactory {
    private final static ViewFactory instance = new ViewFactory();

    public Reader getReader() {
        return  reader;
    }

    private Reader reader = new Reader();
    public static ViewFactory getInstance(){
        return instance;
    }
    private Messenger messenger = new Messenger();

    public Messenger getMessenger() {
        return  messenger;
    }
}
