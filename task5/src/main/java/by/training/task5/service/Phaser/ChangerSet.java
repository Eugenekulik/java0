package by.training.task5.service.Phaser;

import by.training.task5.bean.Iterate;
import by.training.task5.bean.Matrix;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.Phaser;
import java.util.concurrent.locks.Lock;

public class ChangerSet implements Runnable{
    private static final Logger logger = LogManager.getLogger(ChangerSet.class);
    private Integer actual;
    Matrix matrix;
    ConcurrentSkipListSet<Integer> act;
    int value;
    public ChangerSet(Matrix matrix, ConcurrentSkipListSet<Integer> act, int value, Iterate iterate){
        this.act = act;
        this.matrix = matrix;
        this.value = value;
    }
    @Override
    public void run() {
        logger.info(Thread.currentThread() + " start");
        while(true){
            actual = act.pollFirst();
            if (actual==null) {
                break;
            }
            matrix.set(actual, actual, value);
            logger.info(Thread.currentThread() + " change iterate = " + actual);
        }
        logger.info(Thread.currentThread() + "end work");
    }
}
