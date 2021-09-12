package by.training.demoThread.ex4ThreadManagement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger logger  = LogManager.getLogger(Main.class);

    public static void main(String[] args){
        JoinThread t1 = new JoinThread("First");
        JoinThread t2 = new JoinThread("Second");
        t1.start();
        t2.start();
        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info(Thread.currentThread().getName());
    }

}
