package by.training.task2.service;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class CalculateFunctionTest {
    @DataProvider(name = "input_data")
    public Object[][] createData() {
        return
                new Object[][]{
                        {new String[]{"5"},-1},
                        {new String[]{"0"},-0.166666},
                        {new String[]{"2.5"},0.10389},
                        {new String[]{"-3"},-0.0303}
                };
    }

    @Test(description = "Execute test",dataProvider = "input_data")
    public void testExecute(String []a, double c) {
        CalculateFunction test= new CalculateFunction();
        double actual = test.execute(a);
        double expected = c;
        assertEquals(actual,expected,0.0001);

    }
}
