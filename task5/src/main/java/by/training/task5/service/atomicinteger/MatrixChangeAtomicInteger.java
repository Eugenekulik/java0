package by.training.task5.service.atomicinteger;

import by.training.task5.bean.Matrix;
import by.training.task5.service.MatrixChange;
import by.training.task5.service.ServiceException;
import by.training.task5.service.ServiceFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Class realize interface MatrixChange and do dioganal's changes
 * with matrix in some threads and synchronized
 * threads by using AtomicInteger
 */

public class MatrixChangeAtomicInteger implements MatrixChange {
    private Matrix matrix;
    private int[] values;

    /**
     * Constructor
     *
     * @param matrix to be changed
     * @param values array with numbers for threads
     */
    public MatrixChangeAtomicInteger(Matrix matrix, int[] values) {
        this.matrix = matrix;
        this.values = values;
    }

    /**
     * Method start  threads, which do changes
     *
     * @throws ServiceException
     */
    public void change() throws ServiceException {
        List<Thread> threads = new ArrayList<>();
        Random random = ServiceFactory.getInstance().getRandom();
        int countThread = random.nextInt(values[1] - values[0]) + values[0];
        AtomicInteger atomicInteger = new AtomicInteger(0);
        for (int i = 0; i < countThread; i++) {
            threads.add(new Thread(new ChangerAtomicInteger(matrix, values[i + 2], atomicInteger)));
        }
        threads.stream().forEach(Thread::start);
        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            throw new ServiceException(e);
        }
    }
}
