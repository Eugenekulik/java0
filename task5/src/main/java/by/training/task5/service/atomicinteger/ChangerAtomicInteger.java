package by.training.task5.service.atomicinteger;

import by.training.task5.bean.Matrix;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.concurrent.atomic.AtomicInteger;
/**
 * This class implement Functional Interface Runnable and should
 * be passed as an argument in new thread. It fills the diagonal
 * of the matrix with a specific number passed as an argument.
 */
public class ChangerAtomicInteger implements Runnable {
    /**
     * Logger.
     */
    private static final Logger LOGGER = LogManager
            .getLogger(ChangerAtomicInteger.class);
    /**
     * matrix reference , which will be changed.
     */
    private Matrix matrix;
    /**
     * value which will be write in matrix.
     */
    private int value;
    /**
     * AtomicInteger for synchronized with other threads.
     */
    private AtomicInteger atomicInteger;
    /**
     * Constructor.
     *
     * @param matrix        Matrix class which will be changed
     * @param value         integer number used to fill
     * @param atomicInteger used for synchronization with other threads
     */
    public ChangerAtomicInteger(Matrix matrix, int value,
                                AtomicInteger atomicInteger) {
        this.matrix = matrix;
        this.value = value;
        this.atomicInteger = atomicInteger;
    }

    /**
     * main method of class which do changes.
     */
    public void run() {
        LOGGER.info(() -> Thread.currentThread() + " start");
        while (atomicInteger.get() < matrix.getVertical()) {
            int actual = atomicInteger.getAndIncrement();
            matrix.set(actual, actual, value);
            LOGGER.info(() -> Thread.currentThread()
                    + " change iterate = " + actual);
        }
        LOGGER.info(() -> Thread.currentThread() + "end work");
    }
}
