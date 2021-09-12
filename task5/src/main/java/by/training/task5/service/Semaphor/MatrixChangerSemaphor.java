package by.training.task5.service.Semaphor;

import by.training.task5.bean.Iterate;
import by.training.task5.bean.Matrix;
import by.training.task5.service.ReentrantLock.ChangerLock;
import by.training.task5.service.ServiceException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MatrixChangerSemaphor {
    private Matrix matrix;
    private int countThread;
    private int []values;
    public MatrixChangerSemaphor(Matrix matrix, int countThread,int []values){
        this.matrix = matrix;
        this.countThread = countThread;
        this.values = values;
    }
    public void change() throws ServiceException {
        Iterate actual = new Iterate();
        Semaphore sem = new Semaphore(1);
        List<Thread> threads  = new ArrayList<>();
        for(int i = 0;i<countThread;i++){
            threads.add(new Thread(new ChangerSem(matrix,actual,values[i],sem)));
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
