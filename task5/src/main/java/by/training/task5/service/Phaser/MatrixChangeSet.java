package by.training.task5.service.Phaser;

import by.training.task5.bean.Iterate;
import by.training.task5.bean.Matrix;
import by.training.task5.service.MatrixChange;
import by.training.task5.service.ServiceException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.Phaser;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MatrixChangeSet implements MatrixChange {
    private Matrix matrix;
    private int []values;
    public MatrixChangeSet(Matrix matrix, int[] values){
        this.matrix = matrix;
        this.values = values;
    }
    public void change() throws ServiceException {
        Iterate actual = new Iterate();
        Lock locker = new ReentrantLock();
        Phaser phaser = new Phaser(1);
        int countThread = (int)(Math.random()*values[1]+values[0]);
        ConcurrentSkipListSet<Integer> act= new ConcurrentSkipListSet<Integer>();
        for(int i=0;i<matrix.getVertical();i++){
            act.add(i);
        }
        List<Thread> threads  = new ArrayList<>();
        for(int i = 0;i<countThread;i++){
            threads.add(new Thread(new ChangerSet(matrix,act,values[i+2],actual)));
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
