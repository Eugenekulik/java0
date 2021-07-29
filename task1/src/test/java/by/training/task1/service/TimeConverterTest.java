package by.training.task1.service;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class TimeConverterTest {
    @DataProvider(name = "input_num")
    public Object[][] createCorrectData() {
        return
                new Object[][]{
                        {37, "0ч 0м 37сек"},
                        {560, "0ч 9м 20сек"},
                        {6739, "1ч 52м 19сек"},
                        {-400,"invalid number"}
                };
    }

    @Test(description = "numToTime work", dataProvider = "input_num")
    public void testnumToTime(int num,String st) {
        String actual = TimeConverter.numToTime(num);
        String expected = st;
        assertEquals(actual,expected);
    }
}
