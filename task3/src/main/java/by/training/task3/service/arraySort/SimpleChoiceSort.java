package by.training.task3.service.arraySort;

import by.training.task3.bean.MyArray;

/**
 * This class realize  simple choice sort
 * @param <T> any comparable class
 */

public class SimpleChoiceSort<T extends Comparable> implements Sort{
    public SimpleChoiceSort(){}
    public void sort(MyArray array) {
        int exchange;
        int c;
        Object temp = null;
        for (int i = 0; i < array.getSize() - 1; i++) {
            exchange = 0;
            c = i;
            temp = array.get(i);
            for (int j = i + 1; j < array.getSize(); j++) {
                if (array.get(j).compareTo((T) temp) == -1) {
                    c = j;
                    temp = array.get(j);
                    exchange = 1;
                }
            }
            if (exchange != 0) {
                array.set(c, array.get(i));
                array.set(i, (T) temp);
            }
        }
    }
}
