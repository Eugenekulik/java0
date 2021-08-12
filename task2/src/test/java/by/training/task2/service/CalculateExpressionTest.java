package by.training.task2.service;


import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class CalculateExpressionTest {
    @DataProvider(name = "input_data")
    public Object[][] createData() {
        return
                new Object[][]{
                        {new String[]{"5","2","4","6"},196},
                        {new String[]{"0","0","0","0"},0},
                        {new String[]{"2.5","2.7","4.3","1.4"},12.98},
                        {new String[]{"-5","-2","-4","-6"},-172}
                };
    }

    @Test(description = "Execute test",dataProvider = "input_data")
    public void testExecute(String[]a, double c) throws ServiceException{
        CalculateExpression test= new CalculateExpression();
        double actual = test.execute(a);
        double expected = c;
        assertEquals(actual,expected,0.0001);

    }
}
