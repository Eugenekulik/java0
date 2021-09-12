package by.training.task5.bean;

import java.util.Arrays;

public class Matrix {
    private int vertical;
    private int horizontal;
    private int[][] value;

    public Matrix(int n,int m){
        vertical = n;
        horizontal = m;
        value = new int[vertical][horizontal];
        for (int []i:value) {
            for (int j:i) {
                j = 0;
            }
        }
    }

    public int getVertical() {
        return vertical;
    }
    public int getHorizontal() {
        return horizontal;
    }
    public int get(int i,int j){
        return value[i][j];
    }
    public void set(int i,int j,int val){
        value[i][j] = val;
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        for (int[] i:value) {
            for (int j:i) {
                stringBuilder.append(j+" ");
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}
