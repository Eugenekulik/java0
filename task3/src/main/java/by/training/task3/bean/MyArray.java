package by.training.task3.bean;

import java.lang.reflect.Array;

public class MyArray<T extends Comparable> {
    private T[] array=null;
    private int size;
    public MyArray(Class<T> type,int size){
        this.size = size;
        array = (T[]) Array.newInstance(type, size);
        for(int i=0;i<size;i++){
            try{
                array[i]= (T) type.newInstance();
            }
            catch (Exception e){ }
        }
    }
    public void set(int i,T value){
        array[i]=value;
    }
    public T get( int i ) {
        return array[ i ];
    }
    public int getSize(){
        return size;
    }
    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        for(int i=0;i<size;i++){
            stringBuilder.append(array[i]);
            stringBuilder.append(", ");
        }
        return stringBuilder.toString();
    }
}
