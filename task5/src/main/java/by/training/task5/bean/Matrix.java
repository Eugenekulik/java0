package by.training.task5.bean;


public class Matrix {
    private int vertical;
    private int horizontal;
    private int[][] value;

    public Matrix(int n,int m){
        vertical = n;
        horizontal = m;
        value = new int[vertical][horizontal];
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
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            stringBuilder.append("\n");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }
}
