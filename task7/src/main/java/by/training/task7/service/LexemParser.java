package by.training.task7.service;

import by.training.task7.bean.Lexem;
import by.training.task7.bean.TextComponent;
import by.training.task7.bean.Word;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexemParser extends TextParser {
    public LexemParser(TextParser nextParser){
        this.nextParser = nextParser;
    }
    public LexemParser() {super();}
    @Override
    public List<TextComponent> handleParser(String text) {
        List<TextComponent> lexems = new ArrayList<>();
        Matcher matcher = Pattern.compile("([^ !?.]+[ !?.]*)").matcher(text);
        while (matcher.find())
        {
            if(nextParser == null) {
                Lexem lexem = new Lexem();
                String temp = matcher.group(1);
                Matcher matcherDelimiter = Pattern.compile("[.?! ]").matcher(temp);
                while(matcherDelimiter.find()){
                    lexem.setDelimiter(matcherDelimiter.group());
                }
                lexem.addAll(new LeafParser().handleParser(Pattern.compile("[ .?!]+").split(temp)[0]));
                lexems.add(lexem);
            }
            else{
                Lexem lexem = new Lexem();
                String temp = matcher.group(1);
                Matcher matcherDelimiter = Pattern.compile("[.?! ]").matcher(temp);
                while(matcherDelimiter.find()){
                    lexem.setDelimiter(matcherDelimiter.group());
                }
                lexem.addAll(nextParser.handleParser(Pattern.compile("[ .?!]+").split(temp)[0]));
                lexems.add(lexem);
            }
        }
        return lexems;
    }
}
