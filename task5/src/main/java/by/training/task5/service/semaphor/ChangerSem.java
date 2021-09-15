package by.training.task5.service.semaphor;

import by.training.task5.bean.Iterate;
import by.training.task5.bean.Matrix;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.Semaphore;

/**
 * This class implement Functional Interface Runnable and should
 * be passed as an argument in new thread. It fills the diagonal
 * of the matrix with a specific number passed as an argument.
 */
public class ChangerSem implements Runnable {
    private static final Logger logger = LogManager.getLogger(ChangerSem.class);
    private Iterate actual;
    Matrix matrix;
    Semaphore sem;
    int value;

    /**
     * Constructor
     *
     * @param matrix Matrix class which will be changed
     * @param value  integer number used to fill
     * @param sem    used for synchronization with other threads
     * @param actual Iterate class which keep the number actual iterate
     */
    public ChangerSem(Matrix matrix, Iterate actual, int value, Semaphore sem) {
        this.matrix = matrix;
        this.actual = actual;
        this.sem = sem;
        this.value = value;
    }

    /**
     * main method of class which do changes
     */
    public void run() {
        logger.info(() -> Thread.currentThread() + " start");
        try {
            while (true) {
                sem.acquire();
                if (actual.getActual() >= matrix.getVertical()) {
                    sem.release();
                    break;
                }
                int iterate = actual.getActual();
                actual.plus();
                sem.release();
                matrix.set(iterate, iterate, value);
                logger.info(() -> Thread.currentThread() + " change iterate = " + iterate);
            }
        } catch (InterruptedException e) {
            logger.warn(() -> "warning: can't change " + actual.getActual());
            Thread.currentThread().interrupt();
        }
        logger.info(() -> Thread.currentThread() + "end work");
    }
}
