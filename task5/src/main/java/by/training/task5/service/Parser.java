package by.training.task5.service;

import java.util.Arrays;
import java.util.stream.Stream;

public class Parser {
    private String nums;
    public Parser(String nums){
        this.nums = nums;
    }
    public int[] parse() throws ServiceException {
        try {
            String[] data = nums.split(" ");
            int[] a = Stream.of(data).mapToInt(Integer::parseInt).toArray();
            return a;
        }
        catch (NumberFormatException|ClassCastException e){
            throw new ServiceException(e);
        }
    }
}
