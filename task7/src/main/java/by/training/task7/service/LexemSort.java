package by.training.task7.service;

import by.training.task7.bean.Leaf;
import by.training.task7.bean.Lexem;
import by.training.task7.bean.TextComponent;

import java.util.Scanner;
import java.util.function.Consumer;
import java.util.regex.Pattern;

public class LexemSort implements Consumer<TextComponent> {
    private Character symbol;
    @Override
    public void accept(TextComponent textComponent) {
        if(symbol == null){
            return;
        }
        if(!(textComponent.getChild(0) instanceof Lexem)) {
            if(textComponent.getChild(0) instanceof Leaf) {
                return;
            }
            for (TextComponent tc:textComponent.getChildren()) {
                accept(tc);
            }
        }
        TextComponentComparator textComponentComparator = new TextComponentComparator() {
            @Override
            public int compare(TextComponent o1, TextComponent o2) {
                Pattern pattern = Pattern.compile(""+symbol);
                String first = new Scanner(o1.toString()).findInLine(symbol+"");
                String second = new Scanner(o2.toString()).findInLine(symbol+"");
                if(first == null && second == null) {
                    return 0;
                }
                if(first == null) {
                    return -1;
                }
                if(second == null){
                    return 1;
                }
                return first.length() - second.length();
            }
        };
        textComponent.getChildren().sort(textComponentComparator);
    }
    public void setSymbol(char symbol){
        this.symbol = symbol;
    }
}
