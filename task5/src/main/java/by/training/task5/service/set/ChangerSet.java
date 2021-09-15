package by.training.task5.service.set;

import by.training.task5.bean.Matrix;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ConcurrentSkipListSet;
/**
 * This class implement Functional Interface Runnable and should
 * be passed as an argument in new thread. It fills the diagonal
 * of the matrix with a specific number passed as an argument.
 */
public class ChangerSet implements Runnable {
    public static final Logger logger = LogManager.getLogger(ChangerSet.class);
    Matrix matrix;
    ConcurrentSkipListSet<Integer> act;
    int value;
    /**
     * Constructor
     *
     * @param matrix Matrix class which will be changed
     * @param value  integer number used to fill
     * @param act ConcurrentSkipListSet used for synchronization with other threads
     *            and figure out actual iterate
     */
    public ChangerSet(Matrix matrix, ConcurrentSkipListSet<Integer> act, int value) {
        this.act = act;
        this.matrix = matrix;
        this.value = value;
    }

    @Override
    public void run() {
        logger.info(() -> Thread.currentThread() + " start");
        while (true) {
            Integer actual = act.pollFirst();
            if (actual == null) {
                break;
            }
            matrix.set(actual, actual, value);
            logger.info(() -> Thread.currentThread() + " change iterate = " + actual);
        }
        logger.info(() -> Thread.currentThread() + "end work");
    }
}
