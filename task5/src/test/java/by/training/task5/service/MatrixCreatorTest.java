package by.training.task5.service;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class MatrixCreatorTest {

    @DataProvider(name = "correctInput")
    public Object[][] createCorrectData() {
        return
                new Object[][]{
                        {"matrix.txt","0 5 4 3\n" +
                                "4 0 5 7\n" +
                                "1 9 0 1\n" +
                                "2 5 8 0"}
                };
    }

    @Test(description = "testCorrectData",dataProvider = "correctInput")
    public void createTest(String file,String martixString) throws ServiceException {
        String expected = martixString;
        String actual = new MatrixCreator(file).create().toString();
        assertEquals(actual,expected);
    }
}