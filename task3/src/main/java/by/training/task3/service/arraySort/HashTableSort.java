package by.training.task3.service.arraySort;

import by.training.task3.bean.MyArray;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Array;

public class HashTableSort<T extends Comparable> implements Sort {
    private static final Logger logger = LogManager.getLogger(HashTableSort.class);
    public HashTableSort(){};
    public void sort(MyArray array){
        logger.info("Hash table sort run");
        T maxValue = (T)array.get(0);
        int k=3;
        T[] temp = (T[]) Array.newInstance(array.type,array.getSize()*k);
        for(int i=0;i<array.getSize();i++){
            if(maxValue.compareTo(array.get(i))==-1){
                maxValue = (T)array.get(i);
            }
        }
        T minValue = (T)array.get(0);
        for(int i=0;i<array.getSize();i++){
            if(minValue.compareTo(array.get(i))==1){
                minValue =(T) array.get(i);
            }
        }
        double hashKoef = ((double)(k-1)*0.9)*((double) array.getSize()/
                (double) (maxValue.hashCode()-minValue.hashCode()));
        for(int i=0; i<array.getSize(); i++) {
            int hash = (int)(((double)array.get(i).hashCode() - (double)minValue.hashCode())*hashKoef);
            while (temp[hash] != null && temp[hash].compareTo(array.get(i))==-1 ) {
                hash++;
            }
            if (temp[hash] != null) {
                int endpos = hash;
                while(temp[endpos]!=null){
                    endpos++;
                }
                while (endpos!=hash){
                    temp[endpos] = temp[endpos-1];
                    endpos--;
                }
                temp[endpos]=null;
            }
            temp[hash] = (T) array.get(i);
        }
        int j=0;
        for(int i=0;i<temp.length;i++){
            if(temp[i]!=null){
                array.set(j,temp[i]);
                j++;
            }
        }
        logger.info("Hash table sort completed");
    }


}
