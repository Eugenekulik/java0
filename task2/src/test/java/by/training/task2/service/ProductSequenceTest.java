package by.training.task2.service;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ProductSequenceTest {
    @Test(description = "Execute test")
    public void testExecute() {
        ProductSequence test= new ProductSequence();
        double actual = test.execute();
        double expected = 5.745471106375E12;
        assertEquals(actual,expected,0.0001);
    }
}
