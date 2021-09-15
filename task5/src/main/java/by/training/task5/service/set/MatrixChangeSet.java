package by.training.task5.service.set;

import by.training.task5.bean.Matrix;
import by.training.task5.service.MatrixChange;
import by.training.task5.service.ServiceException;
import by.training.task5.service.ServiceFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentSkipListSet;

public class MatrixChangeSet implements MatrixChange {
    private Matrix matrix;
    private int []values;
    public MatrixChangeSet(Matrix matrix, int[] values){
        this.matrix = matrix;
        this.values = values;
    }
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
