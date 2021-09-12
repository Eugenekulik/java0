package by.training.demoThread.ex4ThreadManagement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JoinThread extends Thread{
    private static final Logger logger = LogManager.getLogger(Main.class);
    public JoinThread(String name) {
        super(name);
    }

    public void run() {
        String nameT = getName();
        long timeout = 0;
        logger.info("начало потока " + nameT);
        try {
            switch (nameT) {
                case "First":
                    timeout = 5_000;
                    break;
                case "Second":
                    timeout = 1_000;
            }
            Thread.sleep(timeout);
            logger.info("завершение потока " + nameT);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
