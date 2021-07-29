package by.training.task1.service;

import by.training.task1.bean.Point;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class CalculateDistanceTest {
    @DataProvider(name = "input_Points")
    public Object[][] createCorrectData() {
        return
                new Object[][]{
                        {new Point(4, 6), new Point(4, 7), 1},
                        {new Point(4.5, 5.1), new Point(1.5, 9.1), 5},
                        {new Point(-4, 6), new Point(-1, 2), 5},
                        {new Point(0, 0), new Point(0, 0), 0}
                };
    }

    @Test(description = "betweenPointTest",dataProvider = "input_Points")
    public void testbetweenPoint(Point a, Point b,double c) {
        double actual = CalculateDistance.betweenPoints(a,b);
        double expected = c;
        assertEquals(actual,expected);

    }

}
