package by.training.task7.service;

import by.training.task7.bean.Sentence;
import by.training.task7.bean.TextComponent;
import by.training.task7.bean.Word;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceParser extends TextParser {
    public SentenceParser(TextParser nextParser){
        this.nextParser = nextParser;
    }
    public SentenceParser() {super();}
    @Override
    public List<TextComponent> handleParser(String text) {
        List<TextComponent> sentences = new ArrayList<>();
        Matcher matcher = Pattern.compile("([^!?.]+[.?!]+)").matcher(text);
        while (matcher.find())
        {
            if(nextParser == null) {
                Sentence sentence = new Sentence();
                String temp = matcher.group(1);
                Matcher matcherDelimiter = Pattern.compile("[.?!]").matcher(temp);
                while(matcherDelimiter.find()){
                    sentence.setDelimiter(matcherDelimiter.group());
                }
                sentence.addAll(new LeafParser().handleParser(Pattern.compile("[.?!]+").split(temp)[0]));
                sentences.add(sentence);
            }
            else{
                Sentence sentence = new Sentence();
                String temp = matcher.group(1);
                Matcher matcherDelimiter = Pattern.compile("[.?!]").matcher(temp);
                while(matcherDelimiter.find()){
                    sentence.setDelimiter(matcherDelimiter.group());
                }
                sentence.addAll(nextParser.handleParser(Pattern.compile("[.?!]+").split(temp)[0]));
                sentences.add(sentence);
            }
        }
        return sentences;
    }
}
