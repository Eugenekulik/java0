package by.training.task3.service.arraySort;

import by.training.task3.bean.MyArray;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This class realize bubble sort
 * @param <T> any comparable class
 */
public class BubbleSort<T extends Comparable> implements Sort{
    private static final Logger logger = LogManager.getLogger(BubbleSort.class);
    public BubbleSort(){};
    public void sort(MyArray array){
        logger.info("Bubble sort run");
        for(int i=0;i<array.getSize()-1;i++){
            for(int j=0;j<array.getSize()-i-1;j++){
                if (array.get(j).compareTo(array.get(j+1))==1) {
                    Object temp = array.get(j);
                    array.set(j,array.get(j+1));
                    array.set(j+1,(T)temp);
                }
            }
        }
        logger.info("Bubble sort completed");
    }
}
