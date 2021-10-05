package by.training.task7.bean;

import by.training.task7.service.ExpressionCalculate;

public class Expression extends TextComposite{
    public String getString(){
        StringBuilder stringBuilder = new StringBuilder();
        for (TextComponent tc:components) {
            stringBuilder.append(tc.toString());
        }
        return stringBuilder.toString();
    }
   @Override
    public String toString(){
        ExpressionCalculate expressionCalculate = new ExpressionCalculate();
        return  "" + expressionCalculate.calculate(getString());
    }
}
