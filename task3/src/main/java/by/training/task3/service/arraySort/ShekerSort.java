package by.training.task3.service.arraySort;

import by.training.task3.bean.MyArray;

/**
 * This class realize sheker sort
 * @param <T> any comparable class
 */
public class ShekerSort <T extends Comparable> implements Sort{
    public ShekerSort(){}
    public void sort(MyArray array){
        int left = 0;
        int right = array.getSize()-1;
        int flag = 1;
        while ((left < right) && flag > 0)
        {
            flag = 0;
            for (int i = left; i<right; i++)
            {
                if (array.get(i).compareTo(array.get(i+1))==1)
                {
                    Object temp = array.get(i);
                    array.set(i,array.get(i + 1));
                    array.set(i+1,(T)temp);
                    flag = 1;
                }
            }
            right--;
            for (int i = right; i>left; i--)
            {
                if (array.get(i - 1).compareTo(array.get(i))==1)
                {
                    Object temp = array.get(i);
                    array.set(i,array.get(i - 1));
                    array.set(i-1,(T)temp);
                    flag = 1;
                }
            }
            left++;
        }
    }
}
