package by.training.task5.service.AtomicInteger;

import by.training.task5.bean.Iterate;
import by.training.task5.bean.Matrix;
import by.training.task5.service.MatrixChange;
import by.training.task5.service.ServiceException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class MatrixChangeAtomicInteger implements MatrixChange {
    private Matrix matrix;
    private int[] values;
    public MatrixChangeAtomicInteger(Matrix matrix, int[]values){
        this.matrix = matrix;
        this.values = values;
    }
    public void change() throws ServiceException {
        Iterate actual = new Iterate();
        CountDownLatch countDownLatch = new CountDownLatch(matrix.getVertical());
        List<Thread> threads  = new ArrayList<>();
        int countThread = 3;//(int)(Math.random()*(values[1]-values[0])+values[0]);
        AtomicInteger atomicInteger = new AtomicInteger(0);
        for(int i = 0;i<countThread;i++){
            threads.add(new Thread(new ChangerAtomicInteger(matrix,values[i+2],atomicInteger)));
        }
        threads.stream().forEach(Thread::start);
        try{
            for (Thread thread : threads) {
                thread.join();
            }
        }
        catch (InterruptedException e){
            throw new ServiceException(e);
        }
    }
}
