package by.training.task5.service.reentrantlock;

import by.training.task5.bean.Iterate;
import by.training.task5.bean.Matrix;
import by.training.task5.bean.MatrixException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.locks.Lock;


/**
 * This class implement Functional Interface Runnable and should
 * be passed as an argument in new thread. It fills the diagonal
 * of the matrix with a specific number passed as an argument.
 */
public class ChangerLock implements Runnable {
    /**
     * Logger.
     */
    private static final Logger LOGGER = LogManager
            .getLogger(ChangerLock.class);
    /**
     * Class Iterate give oppotunity to figure out actual iterate.
     */
    private Iterate actual;
    /**
     * Matrix reference which will be changed.
     */
    private Matrix matrix;
    /**
     * Locker for synchronization with other threads.
     */
    private Lock locker;
    /**
     * The value which to be written to the matrix.
     */
    private int value;
    /**
     * Constructor.
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
     * main method of class which do changes.
     */
    @Override
    public void run() {
        LOGGER.info(() -> Thread.currentThread() + " start");
        while (true) {
            locker.lock();
            if (actual.getActual() >= matrix.getVertical()) {
                locker.unlock();
                break;
            }
            int iterate = actual.getActual();
            actual.plus();
            locker.unlock();
            try {
                matrix.set(iterate, iterate, value);
            }
            catch (MatrixException e){
                LOGGER.info(e);
                Thread.currentThread().interrupt();
            }
            LOGGER.info(() -> Thread.currentThread()
                    + " change iterate = " + iterate);
        }
        LOGGER.info(() -> Thread.currentThread() + "end work");
    }
}
