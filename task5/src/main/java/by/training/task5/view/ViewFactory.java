package by.training.task5.view;

public class ViewFactory {
    private static final ViewFactory instance = new ViewFactory();
    private final Messanger messanger = new Messanger();

    public static ViewFactory getInstance() {
        return instance;
    }

    public Messanger getMessanger() {
        return messanger;
    }
}
