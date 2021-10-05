package by.training.task7.service;

import by.training.task7.bean.Lexem;
import by.training.task7.bean.Paragraph;
import by.training.task7.bean.TextComponent;
import by.training.task7.bean.Word;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParagraphParser extends TextParser {
    public ParagraphParser(TextParser nextParser){
        this.nextParser = nextParser;
    }

    public ParagraphParser() {super();}

    @Override
    public List<TextComponent> handleParser(String text) {
        List<TextComponent> paragraphes = new ArrayList<>();
        Matcher matcher = Pattern.compile("([\\n]*[\\t][^\\t]+[\\n]*)").matcher(text);
        while (matcher.find())
        {
            if(nextParser == null) {
                Paragraph paragraph = new Paragraph();
                paragraph.addAll(new LeafParser().handleParser(matcher.group(1).substring(1)));
                paragraphes.add(paragraph);
            }
            else{
                Paragraph paragraph = new Paragraph();
                paragraph.addAll(nextParser.handleParser(matcher.group(1).substring(1)));
                paragraphes.add(paragraph);
            }
        }
        return paragraphes;
    }
}
