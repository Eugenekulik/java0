package by.training.demoThread.ex5aThreadThreadToDisable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ThreadToDisable implements Runnable {
    private static final Logger logger = LogManager.getLogger(Main.class);
    private boolean isActive;

    void disable() {
        isActive = false;
    }

    ThreadToDisable() {
        isActive = true;
    }

    public void run() {

        logger.info("Поток " + Thread.currentThread().getName() + " начал работу... ");
        int counter = 1; // счетчик циклов
        while (isActive) {
            logger.info("Цикл " + counter++);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                logger.info("Поток прерван");
            }
        }
        logger.info("Поток " + Thread.currentThread().getName() + " завершил работу... ");
    }
}

