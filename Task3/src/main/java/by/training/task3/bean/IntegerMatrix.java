package by.training.task3.bean;

import java.util.ArrayList;

/**
 * This class extends MyMatrix and expands functionality for Integer arguments
 */
public class IntegerMatrix extends MyMatrix<Integer>{

    public IntegerMatrix(int n, int m) {
        super(n, m);
    }
    public IntegerMatrix(ArrayList<Integer> temp, int n, int m){
        super(temp, n, m);
    }
}
