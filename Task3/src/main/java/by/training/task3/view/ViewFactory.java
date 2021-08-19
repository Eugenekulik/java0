package by.training.task3.view;




public class ViewFactory {
    private final static ViewFactory instance = new ViewFactory();
    private ViewFactory(){};
    private Reader reader = new Reader();
    private Messenger messenger =new Messenger();
    public static  ViewFactory getInstance(){
        return instance;
    }
    public Reader getReader(String readerChoose){
        return reader;
    }
    public Messenger getMessenger(String messengerChoose) {
        return messenger;
    }
}
