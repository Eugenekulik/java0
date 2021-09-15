package by.training.task5.service.reentrantlock;

import by.training.task5.bean.Iterate;
import by.training.task5.bean.Matrix;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.locks.Lock;


/**
 * This class implement Functional Interface Runnable and should
 * be passed as an argument in new thread. It fills the diagonal
 * of the matrix with a specific number passed as an argument.
 */
public class ChangerLock implements Runnable {
    private static final Logger logger = LogManager.getLogger(ChangerLock.class);
    private Iterate actual;
    Matrix matrix;
    Lock locker;
    int value;

    /**
     * Constructor
     *
     * @param matrix Matrix class which will be changed
     * @param value  integer number used to fill
     * @param locker used for synchronization with other threads
     * @param actual Iterate class which keep the number actual iterate
     */
    public ChangerLock(Matrix matrix, Iterate actual, int value, Lock locker) {
        this.matrix = matrix;
        this.actual = actual;
        this.locker = locker;
        this.value = value;
    }

    /**
     * main method of class which do changes
     */
    @Override
    public void run() {
        logger.info(() -> Thread.currentThread() + " start");
        while (true) {
            locker.lock();
            if (actual.getActual() >= matrix.getVertical()) {
                locker.unlock();
                break;
            }
            int iterate = actual.getActual();
            actual.plus();
            locker.unlock();
            matrix.set(iterate, iterate, value);
            logger.info(() -> Thread.currentThread() + " change iterate = " + iterate);
        }
        logger.info(() -> Thread.currentThread() + "end work");
    }
}
