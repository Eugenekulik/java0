package by.training.task2.service;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class RedistributionTest {
    @DataProvider(name = "input_data")
    public Object[][] createData() {
        return
                new Object[][]{
                        {new String[]{"5","2"},new double[]{5,2}},
                        {new String[]{"0","0"},new double[]{0,0}},
                        {new String[]{"2.5","2.7"},new double[]{2.7,2.5}},
                        {new String[]{"-5","-2"},new double[]{-2,-5}}
                };
    }

    @Test(description = "Execute test",dataProvider = "input_data")
    public void testExecute(String[]a, double []c) throws ServiceException{
        Redistribution test= new Redistribution();
        double []actual = test.execute(a);
        double []expected = c;
        assertEquals(actual,expected,0.0001);

    }
}
