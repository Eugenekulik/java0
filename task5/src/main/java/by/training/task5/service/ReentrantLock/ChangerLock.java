package by.training.task5.service.ReentrantLock;

import by.training.task5.bean.Iterate;
import by.training.task5.bean.Matrix;
import by.training.task5.controller.Runner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.locks.Lock;

public class ChangerLock implements Runnable{
    private static final Logger logger = LogManager.getLogger(Runner.class);
    private Iterate actual;
    Matrix matrix;
    Lock locker;
    int value;
    public ChangerLock(Matrix matrix, Iterate actual, int value, Lock locker){
        this.matrix = matrix;
        this.actual = actual;
        this.locker = locker;
        this.value = value;
    }
    @Override
    public void run() {
        logger.info(Thread.currentThread() + " start");
        while(true) {
            locker.lock();
            if (actual.getActual() >= matrix.getVertical()) {
                locker.unlock();
                break;
            }
            int iterate = actual.getActual();
            actual.plus();
            locker.unlock();
            matrix.set(iterate, iterate, value);
            logger.info(Thread.currentThread() + " change iterate = " + iterate);
        }
        logger.info(Thread.currentThread() + "end work");
    }
}
