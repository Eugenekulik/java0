package by.training.task7.service;

import by.training.task7.bean.Expression;
import by.training.task7.bean.TextComponent;
import by.training.task7.bean.TextComposite;
import by.training.task7.bean.Word;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordExpressionParser extends TextParser {
    public WordExpressionParser(TextParser textParser){
        this.nextParser = textParser;
    }
    public WordExpressionParser() {super();}
    @Override
    public List<TextComponent> handleParser(String text) {
        List<TextComponent> words = new ArrayList<>();
        Matcher matcher = Pattern.compile("([\n]*[^ ]+[ ]*)").matcher(text);
        while (matcher.find())
        {
            if(nextParser == null) {
                String temp = matcher.group(1);
                TextComposite word = null;
                if(checkExpression(temp)) {
                    word = new Expression();
                }
                else {
                    word = new Word();
                }
                word.addAll(new LeafParser().handleParser(temp));
                words.add(word);
            }
            else{
                String temp = matcher.group(1);
                TextComposite word = null;
                if(checkExpression(temp)) {
                    word = new Expression();
                }
                else {
                    word = new Word();
                }
                word.addAll(nextParser.handleParser(matcher.group(1)));
                words.add(word);
            }
        }
        return words;
    }
    private boolean checkExpression(String text){
        Matcher matcher = Pattern.compile("[^0-9()~|&^<>\n]+[ .?!]*").matcher(text);
        if(matcher.find()) {
            return false;
        }
        return true;
    }
}
