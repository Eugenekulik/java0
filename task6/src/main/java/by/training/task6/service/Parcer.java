package by.training.task6.service;

import java.util.stream.Stream;

public class Parcer {
    /**
     * This method execute splitting and parsing.
     * @param nums String which contains array of int values
     * @return array of double
     * @throws ServiceException Exception for service layer
     */
    public double[] parse(String nums) throws ServiceException {
        try {
            String[] data = nums.split(" ");
            return Stream.of(data).mapToDouble(Double::parseDouble).toArray();
        } catch (NumberFormatException | ClassCastException e) {
            throw new ServiceException(e);
        }
    }
}
