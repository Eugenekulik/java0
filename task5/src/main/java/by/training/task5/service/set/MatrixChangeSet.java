package by.training.task5.service.set;

import by.training.task5.bean.Matrix;
import by.training.task5.service.MatrixChange;
import by.training.task5.service.ServiceException;
import by.training.task5.service.ServiceFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * Class realize interface MatrixChange and do dioganal's changes
 * with matrix in some threads and synchronized
 * threads by using ConcurrentSkipListSet.
 */
public class MatrixChangeSet implements MatrixChange {
    /**
     * Matrix reference which will be changed.
     */
    private Matrix matrix;
    /**
     * The value which will be written to the matrix.
     */
    private int[] values;
    /**
     * Constructor.
     *
     * @param matrix to be changed
     * @param values array with numbers for threads
     */
    public MatrixChangeSet(Matrix matrix, int[] values) {
        this.matrix = matrix;
        this.values = values;
    }
    /**
     * Method start  threads, which do changes.
     *
     * @throws ServiceException Exception for service layer
     */
    public void change() throws ServiceException {
        Random random = ServiceFactory.getInstance().getRandom();
        int countThread = random.nextInt(values[1] - values[0]) + values[0];
        ConcurrentSkipListSet<Integer> act = new ConcurrentSkipListSet<>();
        for (int i = 0; i < matrix.getVertical(); i++) {
            act.add(i);
        }
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < countThread; i++) {
            threads.add(new Thread(new ChangerSet(matrix, act, values[i + 2])));
        }
        threads.stream().forEach(Thread::start);
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new ServiceException(e);
            }
        }


    }
}
