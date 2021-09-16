package by.training.task5.service;

import java.util.stream.Stream;

/**
 * Class which parse to int string of numbers.
 */
public class Parser {
    /**
     * This method execute splitting and parsing.
     * @param nums String which contains array of int values
     * @return array of int
     * @throws ServiceException Exception for service layer
     */
    public int[] parse(String nums) throws ServiceException {
        try {
            String[] data = nums.split(" ");
            return Stream.of(data).mapToInt(Integer::parseInt).toArray();
        } catch (NumberFormatException | ClassCastException e) {
            throw new ServiceException(e);
        }
    }
}
