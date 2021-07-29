package by.training.task1.service;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
public class RectangleSquareTest {
        @DataProvider(name = "input_num")
        public Object[][] createCorrectData() {
            return
                    new Object[][]{
                            {20, 800},
                            {-4, 0},
                            {0, 0},
                            {4.6, 42.32}
                    };
        }

        @Test(description = "square find test", dataProvider = "input_num")
        public void testsquare(double a,double st) {
            double actual = RectangleSquare.square(a);

            double expected = st;
            assertEquals(actual,expected,0.0001);
        }

}
