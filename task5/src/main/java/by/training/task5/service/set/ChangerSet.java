package by.training.task5.service.set;

import by.training.task5.bean.Matrix;
import by.training.task5.bean.MatrixException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ConcurrentSkipListSet;
/**
 * This class implement Functional Interface Runnable and should
 * be passed as an argument in new thread. It fills the diagonal
 * of the matrix with a specific number passed as an argument.
 */
public class ChangerSet implements Runnable {
    /**
     * Logger.
     */
    public static final Logger LOGGER = LogManager.getLogger(ChangerSet.class);
    /**
     * Matrix reference which to be changed.
     */
    private Matrix matrix;
    /**
     * Class ConcurrentSkipListSet for synchronization with other threads.
     */
    private ConcurrentSkipListSet<Integer> act;
    /**
     * The value which to be written to the matrix.
     */
    private int value;
    /**
     * Constructor.
     *
     * @param matrix Matrix class which will be changed
     * @param value  integer number used to fill
     * @param act ConcurrentSkipListSet used for synchronization with
     *            other threads and figure out actual iterate
     */
    public ChangerSet(Matrix matrix,
                      ConcurrentSkipListSet<Integer> act,
                      int value) {
        this.act = act;
        this.matrix = matrix;
        this.value = value;
    }

    /**
     * main method of class which do changes.
     */
    public void run() {
        LOGGER.info(() -> Thread.currentThread() + " start");
        while (true) {
            Integer actual = act.pollFirst();
            if (actual == null) {
                break;
            }
            try {
                matrix.set(actual, actual, value);
            } catch (MatrixException e){
                LOGGER.info(e);
                Thread.currentThread().interrupt();
            }
            LOGGER.info(() -> Thread.currentThread()
                    + " change iterate = " + actual);
        }
        LOGGER.info(() -> Thread.currentThread() + "end work");
    }
}
