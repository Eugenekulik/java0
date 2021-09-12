package by.training.task5.service.ReentrantLock;

import by.training.task5.bean.Iterate;
import by.training.task5.bean.Matrix;
import by.training.task5.service.ServiceException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MatrixChangeLock {
    private Matrix matrix;
    private int countThread;
    private int []values;
    public MatrixChangeLock(Matrix matrix, int countThread, int[] values){
        this.matrix = matrix;
        this.countThread = countThread;
        this.values = values;
    }
    public void change() throws ServiceException {
        Iterate actual = new Iterate();
        Lock locker = new ReentrantLock(true);
        List<Thread> threads  = new ArrayList<>();
        for(int i = 0;i<countThread;i++){
            threads.add(new Thread(new ChangerLock(matrix,actual,values[i],locker)));
        }
        threads.stream().forEach(Thread::start);
        for (Thread thread : threads) {
            try {
                thread.join();
            }
            catch (InterruptedException e){
                throw new ServiceException(e);
            }
        }
    }
}
