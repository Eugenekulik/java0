package by.training.task2.service;

import static java.lang.Math.pow;

/**
 * This class calculate function depending on the value x
 */
public class CalculateFunction {
    public CalculateFunction(){};

    /**
     * This method do calculations
     * @param args
     * @return double result
     * @throws ServiceException
     */
    public double execute(String []args) throws ServiceException {
        double x = Double.parseDouble(args[0]);
        try {
            if (x < 3) {
                return 1 / (pow(x, 3) - 6);
            } else {
                return -pow(x, 2) + x * 3 + 9;
            }
        }
        catch (ArithmeticException e){
            throw new ServiceException("devide by zero", e);
        }
    }

}
