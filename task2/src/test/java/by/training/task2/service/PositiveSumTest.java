package by.training.task2.service;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class PositiveSumTest {
    @DataProvider(name = "input_data")
    public Object[][] createData() {
        return
                new Object[][]{
                        {new String[]{"5","2","4"},"This numbers can give positive sum."},
                        {new String[]{"0","0","0"},"This numbers can't give positive sum."},
                        {new String[]{"2.5","2.7","4.3"},"This numbers can give positive sum."},
                        {new String[]{"-5","-2","-4"},"This numbers can't give positive sum."}
                };
    }

    @Test(description = "Execute test",dataProvider = "input_data")
    public void testExecute(String[]a, String c) throws ServiceException{
        PositiveSum test= new PositiveSum();
        String actual = test.execute(a);
        String expected = c;
        assertEquals(actual,expected);

    }
}
