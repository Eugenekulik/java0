package by.training.task2.service;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class CurrentStringTest {
    @DataProvider(name = "input_data")
    public Object[][] createData() {
        return
                new Object[][]{
                        {"Hello",true},
                        {"_Goodday",true},
                        {"It's mistake",false},
                        {"2day",false}
                };
    }

    @Test(description = "Execute test",dataProvider = "input_data")
    public void testExecute(String a, boolean c) throws ServiceException{
        CurrentString test= new CurrentString();
        boolean actual = test.execute(a);
        boolean expected = c;
        assertEquals(actual,expected);

    }
}
