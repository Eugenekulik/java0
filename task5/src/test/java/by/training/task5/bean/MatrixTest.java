package by.training.task5.bean;

import by.training.task5.service.MatrixCreator;
import by.training.task5.service.ServiceException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class MatrixTest {

    @DataProvider(name = "correctInput")
    public Object[][] createCorrectData() {
        return
                new Object[][]{
                        {new int[]{3,3},"0 0 0\n" +
                                "0 0 0\n" +
                                "0 0 0"}
                };
    }

    @Test(description = "testCorrectData",dataProvider = "correctInput")
    public void MatrixTest(int[] size,String martixString) throws ServiceException {
        String expected = martixString;
        String actual = new Matrix(size[0],size[1]).toString();
        assertEquals(actual,expected);
    }
}