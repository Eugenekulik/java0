package by.training.task3.bean;

import java.util.ArrayList;

public class MyMatrix<T extends Number>  {
    private ArrayList<T> matrixValue = new ArrayList<T>();
    private  int verticalSize;
    private  int horizontalSize;
    public  MyMatrix(int n,int m){
        verticalSize=m;
        horizontalSize =n;
    }
    public MyMatrix(ArrayList<T> temp, int n, int m){
        matrixValue=temp;
        verticalSize=m;
        horizontalSize =n;
    }

    public int getVerticalSize() {
        return verticalSize;
    }

    public int getHorizontalSize() {
        return horizontalSize;
    }
    @Override
    public String toString(){
        StringBuffer str=new StringBuffer("");
        for(int i=0;i<verticalSize;i++){
            for(int j = 0; j< horizontalSize; j++){
                str.append(matrixValue.get(j*verticalSize+i)+"\t");
            }
            str.append("\n");
        }
        return str.toString();
    }

}
