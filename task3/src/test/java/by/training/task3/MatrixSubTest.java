package by.training.task3;

import by.training.task3.bean.IntegerMatrix;
import by.training.task3.service.MatrixManager;
import by.training.task3.service.matrixOperation.MatrixSub;
import by.training.task3.service.matrixOperation.MatrixSum;
import by.training.task3.service.ServiceException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static org.testng.Assert.assertEquals;

public class MatrixSubTest {
    @DataProvider(name = "input_matrix")
    public Object[][] createCorrectData() {
        return
                new Object[][]{
                        {new int[]{0,0,0,0,0,0,0,0,0},3,3,new int[]{0,0,0,0,0,0,0,0,0},3,3,new int[]{0,0,0,0,0,0,0,0,0},3,3},
                        {new int[]{5,5,5,5,5,5,5,5,5},3,3,new int[]{2,2,2,2,2,2,2,2,2},3,3,new int[]{3,3,3,3,3,3,3,3,3},3,3},
                        {new int[]{-5,-5,-5,-5,-5,-5,-5,-5,-5},3,3,new int[]{-3,-3,-3,-3,-3,-3,-3,-3,-3},3,3,
                                new int[]{-2,-2,-2,-2,-2,-2,-2,-2,-2},3,3},
                };
    }

    @Test(description = "matrix sub test", dataProvider = "input_matrix")
    public void testResult(int[] a,int i1,int j1,int[]b,int i2,int j2, int[]c,int i3,int j3) throws ServiceException {
        ArrayList<Integer> array1= new ArrayList<>();
        for (int temp:a) {
            array1.add(temp);
        }
        ArrayList<Integer> array2= new ArrayList<>();
        for (int temp:b) {
            array2.add(temp);
        }
        ArrayList<Integer> array3= new ArrayList<>();
        for (int temp:c) {
            array3.add(temp);
        }
        IntegerMatrix matrix1 = new IntegerMatrix(array1,i1,j1);
        IntegerMatrix matrix2 = new IntegerMatrix(array2,i2,j2);
        IntegerMatrix expected = new IntegerMatrix(array3,i3,j3);
        MatrixSub matrixSub = new MatrixSub();
        IntegerMatrix actual = matrixSub.result(matrix1,matrix2);
        assertEquals(actual,expected);
    }
    @DataProvider(name = "input_matrix_exception")
    public Object[][] createExceptionData() {
        return
                new Object[][]{
                        {new int[]{0,0,0,0,0,0,0,0},3,3,new int[]{0,0,0,0,0,0,0,0,0},3,3,new int[]{0,0,0,0,0,0,0,0,0},3,3},
                        {new int[]{1,1,1,1,1,1,1,1,1},3,2,new int[]{2,2,2,2,2,2,2,2,2},3,3,new int[]{3,3,3,3,3,3,3,3,3},3,3},
                        {new int[]{-5,-5,-5,-5,-5,-5,-5,-5,-5},2,3,new int[]{-3,-3,-3,-3,-3,-3,-3,-3,-3},3,3,
                                new int[]{-8,-8,-8,-8,-8,-8,-8,-8,-8},3,3},
                };
    }

    @Test(expectedExceptions = {ServiceException.class}, dataProvider = "input_matrix_exception")
    public void testResultException(int[] a,int i1,int j1,int[]b,int i2,
                                    int j2, int[]c,int i3,int j3) throws ServiceException {
        ArrayList<Integer> array1= new ArrayList<>();
        for (int temp:a) {
            array1.add(temp);
        }
        ArrayList<Integer> array2= new ArrayList<>();
        for (int temp:b) {
            array2.add(temp);
        }
        ArrayList<Integer> array3= new ArrayList<>();
        for (int temp:c) {
            array3.add(temp);
        }
        MatrixManager matrixManager = MatrixManager.getInstance();
        IntegerMatrix matrix1 = matrixManager.createIntegerMatrix(array1,i1,j1);
        IntegerMatrix matrix2 = matrixManager.createIntegerMatrix(array2,i2,j2);
        IntegerMatrix expected = matrixManager.createIntegerMatrix(array3,i3,j3);
        MatrixSub matrixSub = new MatrixSub();
        IntegerMatrix actual = matrixSub.result(matrix1,matrix2);
    }
}
