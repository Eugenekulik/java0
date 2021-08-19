package by.training.task3.service.arraySort;

import by.training.task3.bean.MyArray;

public class BubbleSort<T extends Comparable> {
    public BubbleSort(){};
    public void sort(MyArray array){
        for(int i=0;i<array.getSize()-1;i++){
            for(int j=0;j<array.getSize()-i-1;j++){
                if (array.get(j).compareTo(array.get(j+1))==1) {
                    Object temp = array.get(j);
                    array.set(j,array.get(j+1));
                    array.set(j+1,(T)temp);
                }
            }
        }
    }
}
