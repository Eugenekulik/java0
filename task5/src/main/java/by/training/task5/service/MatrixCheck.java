package by.training.task5.service;

import by.training.task5.bean.Matrix;

public class MatrixCheck {
    private Matrix matrix;
    public MatrixCheck(Matrix matrix){
        this.matrix = matrix;
    }
    public boolean check(){
        if(matrix.getHorizontal()!= matrix.getVertical()){
            return false;
        }
        for(int i = 0; i<matrix.getVertical();i++){
            if(matrix.get(i,i)==0){
                return false;
            }
        }
        return true;
    }
}
