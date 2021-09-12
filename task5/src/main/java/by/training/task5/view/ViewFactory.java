package by.training.task5.view;

public class ViewFactory {
    private static final ViewFactory instance = new ViewFactory();

    public ViewFactory getInstance(){
        return instance;
    }
}
