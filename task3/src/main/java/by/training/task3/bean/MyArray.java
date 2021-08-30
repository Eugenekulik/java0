package by.training.task3.bean;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Objects;

/**
 * This class is wrapper of array
 * @param <T> it's a comparable class
 */

public class MyArray<T extends Comparable> {
    private T[] array=null;
    private int size;
    public Class type;
    public MyArray(Class<T> type,int size){
        this.size = size;
        this.type = type;
        array = (T[]) Array.newInstance(type, size);
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
        for (int i = 0; i < size; i++) {
            stringBuilder.append(array[i]);
            stringBuilder.append(", ");
        }
        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyArray<?> myArray = (MyArray<?>) o;
        return size == myArray.size && Arrays.equals(array, myArray.array);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(size);
        result = 31 * result + Arrays.hashCode(array);
        return result;
    }
}
