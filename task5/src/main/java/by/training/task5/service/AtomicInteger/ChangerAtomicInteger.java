package by.training.task5.service.AtomicInteger;

import by.training.task5.bean.Matrix;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.atomic.AtomicInteger;

public class ChangerAtomicInteger implements Runnable{
    private static final Logger logger = LogManager.getLogger(ChangerAtomicInteger.class);
    private Matrix matrix;
    private int value;
    private AtomicInteger atomicInteger;
    public ChangerAtomicInteger(Matrix matrix, int value, AtomicInteger atomicInteger){
        this.matrix = matrix;
        this.value = value;
        this.atomicInteger = atomicInteger;
    }
    public void run(){
        logger.info(Thread.currentThread() + " start");
        while(atomicInteger.get()<matrix.getVertical()) {
            int actual = atomicInteger.getAndIncrement();
            matrix.set(actual, actual, value);
            logger.info(Thread.currentThread() + " change iterate = " + actual);
        }
        logger.info(Thread.currentThread() + "end work");
    }
}
