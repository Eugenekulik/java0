package by.training.task7.service;


import by.training.task7.bean.Expression;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExpressionCalculate {
    public int calculate(String expression){
        Matcher matcher = Pattern.compile("[0-9]+|>+|<+|&|~|\\^").matcher(expression);
        List<String> strings = new ArrayList<>();
        while (matcher.find()){
            strings.add(matcher.group());
        }
        return 1;
    }
}
