package by.training.task2.service;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class CalculateFunctionTableTest {
    @DataProvider(name = "input_data")
    public Object[][] createData() {
        return
                new Object[][]{
                        {new String[]{"1","3","1"},new double[]{1,0.8414709848078965,
                                                                2,0.9092974268256817,
                                                                3,0.1411200080598672}},
                        {new String[]{"-3","-1","1"},new double[]{-3,-0.1411200080598672,
                                                                    -2,-0.9092974268256817,
                                                                    -1,-0.8414709848078965}},
                        {new String[]{"2.5","3.5","0.5"},new double[]{2.5,0.5984721441039564,
                                                                    3,0.1411200080598672,
                                                                    3.5,-0.35078322768961984}}
                };
    }

    @Test(description = "Execute test data",dataProvider = "input_data")
    public void testExecute(String[]a, double []c) {
        CalculateFunctionTable test= new CalculateFunctionTable();
        double []actual = test.execute(a);
        double []expected = c;
        assertEquals(actual,expected,0.0001);

    }
    @Test(enabled = true, expectedExceptions = ArithmeticException.class,
            expectedExceptionsMessageRegExp = "h can't be 0")
    public void testConvertCelsiusToFahrenheitException() {
        CalculateFunctionTable c= new CalculateFunctionTable();
        c.execute(new String[]{"0","0","0"});
    }
}
