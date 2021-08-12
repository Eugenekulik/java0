package by.training.task2.service;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class MathTaskTest {
    @Test(description = "execute test")
    public void testExecute()throws ServiceException{
        MathTask m = new MathTask();
        int[] aqtual = m.execute();
        int[] expected={51,48};
        assertEquals(aqtual,expected);
    }
}
