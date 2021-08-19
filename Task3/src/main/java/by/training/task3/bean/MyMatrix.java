package by.training.task3.bean;

import javax.management.MBeanException;
import java.util.ArrayList;
import java.util.Objects;

public class MyMatrix<T extends Number>  {
    private ArrayList<T> matrixValue = new ArrayList<T>();
    private  int verticalSize;
    private  int horizontalSize;
    public  MyMatrix(int n,int m){
        verticalSize=m;
        horizontalSize =n;
        for(int i=0;i<m*n;i++){
            matrixValue.add(null);
        }
    }
    public MyMatrix(ArrayList<T> temp, int n, int m) {
        matrixValue=temp;
        verticalSize=m;
        horizontalSize =n;
    }
    public T getElement(int i, int j){
        return matrixValue.get(i*horizontalSize+j);
    }
    public void setElement(int i,int j,T a){
        matrixValue.set(i*horizontalSize+j,a);
    }

    public int getVerticalSize() {
        return verticalSize;
    }

    public int getHorizontalSize() {
        return horizontalSize;
    }
    @Override
    public String toString(){
        StringBuilder str=new StringBuilder("");
        for(int i=0;i<verticalSize;i++){
            for(int j = 0; j< horizontalSize; j++){
                str.append(matrixValue.get(j*verticalSize+i)+"\t");
            }
            str.append("\n");
        }
        return str.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyMatrix<?> myMatrix = (MyMatrix<?>) o;
        return Objects.equals(matrixValue, myMatrix.matrixValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matrixValue);
    }
}
