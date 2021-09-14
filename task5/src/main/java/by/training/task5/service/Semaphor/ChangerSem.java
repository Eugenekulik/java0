package by.training.task5.service.Semaphor;

import by.training.task5.bean.Iterate;
import by.training.task5.bean.Matrix;
import by.training.task5.controller.Runner;
import by.training.task5.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;

public class ChangerSem implements Runnable{
    private static final Logger logger = LogManager.getLogger(ChangerSem.class);
    private Iterate actual;
    Matrix matrix;
    Semaphore sem;
    int value;
    public ChangerSem(Matrix matrix, Iterate actual, int value, Semaphore sem){
        this.matrix = matrix;
        this.actual = actual;
        this.sem = sem;
        this.value = value;
    }
    public void run() {
        logger.info(Thread.currentThread() + " start");
        try {
            while (true) {
                sem.acquire();
                if (actual.getActual() >= matrix.getVertical()) {
                    sem.release();
                    break;
                }
                int iterate = actual.getActual();
                actual.plus();
                sem.release();
                matrix.set(iterate, iterate, value);
                logger.info(Thread.currentThread() + " change iterate = " + iterate);
            }
        }
        catch (InterruptedException e){
            logger.warn("warning: can't change " + actual.getActual());
        }
        logger.info(Thread.currentThread() + "end work");
    }
}
