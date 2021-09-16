package by.training.task5.view;

/**
 * This Class is Singleton for view layer realize pattern factory.
 * Contains Classes gives oppotunity interact with user.
 */
public class ViewFactory {
    /**
     * Single Instance of ViewFactory.
     */
    private static final ViewFactory INSTANCE = new ViewFactory();
    /**
     * Single Instance of Messenger.
     */
    private final Messenger messenger = new Messenger();
    /**
     * Getter for single instance of ViewFactory.
     * @return ViewFactory
     */
    public static ViewFactory getInstance() {
        return INSTANCE;
    }
    /**
     * Getter for Messenger.
     * @return Messenger
     */
    public Messenger getMessenger() {
        return messenger;
    }
}
