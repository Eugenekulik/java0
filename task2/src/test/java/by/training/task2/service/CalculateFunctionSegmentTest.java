package by.training.task2.service;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class CalculateFunctionSegmentTest {
    @DataProvider(name = "input_data")
    public Object[][] createData() {
        return
                new Object[][]{
                        {new String[]{"5","10","1"},new double[]{5.0,6.0,7.0,8.0,9.0,10.0}},
                        {new String[]{"0","0","0"},new double[]{0.0}},
                        {new String[]{"-6","-1","1"},new double[]{6.0,5.0,4.0,3.0,2.0,1.0}},
                        {new String[]{"5.3","6.3","0.2"},new double[]{5.3,5.5,5.7,5.9,6.1,6.3}}
                };
    }

    @Test(description = "Execute test",dataProvider = "input_data")
    public void testExecute(String []a, double[] c) {
        CalculateFunctionSegment test= new CalculateFunctionSegment();
        double[] actual = test.execute(a);
        double[] expected = c;

        for(int i=0;i<actual.length;i++){
            assertEquals(actual[i],expected[i],0.0001);
        }
    }
}
