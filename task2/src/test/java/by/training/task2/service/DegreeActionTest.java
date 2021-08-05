package by.training.task2.service;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class DegreeActionTest {
    @DataProvider(name = "input_data")
    public Object[][] createData() {
        return
                new Object[][]{
                        {new String[]{"5","2","4"},new double[]{125,8,64}},
                        {new String[]{"0","0","0"},new double[]{0,0,0}},
                        {new String[]{"2.5","2.7","4.3"},new double[]{15.625,19.683,79.507}},
                        {new String[]{"-5","-2","-4"},new double[]{625,16,256}}
                };
    }

    @Test(description = "Execute test",dataProvider = "input_data")
    public void testExecute(String[]a, double []c) {
        DegreeAction test= new DegreeAction();
        double []actual = test.execute(a);
        double []expected = c;
        assertEquals(actual,expected,0.0001);

    }
}