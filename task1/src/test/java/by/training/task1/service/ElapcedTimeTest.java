package by.training.task1.service;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ElapcedTimeTest {
    @DataProvider(name = "input_time")
    public Object[][] createCorrectData() {
        return
                new Object[][]{
                        {new int[]{10, 30, 12, 7, 11, 25}, "17ч 41м 37сек"},
                        {new int[]{0, 0, 0, 0, 0, 0}, "0ч 0м 0сек"},
                        {new int[]{-10, -30, -17, -7, -11, -25}, "invalid number"},
                        {new int[]{7, 20, 40, 50, 55, 31}, "10ч 16м 11сек"}
                };
    }

    @Test(description = "numToTime work", dataProvider = "input_time")
    public void testcalculate(int[] a, String st) {
        String actual = ElapcedTime.calculate(a[0], a[1], a[2], a[3], a[4], a[5]);
        String expected = st;
        assertEquals(actual, expected);
    }
}
