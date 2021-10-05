package by.training.task7;

import by.training.task7.bean.Expression;
import by.training.task7.bean.Text;
import by.training.task7.service.*;

public class Runner {
    public static void main(String[] args){
        String text = "\tIt has survived - not only (five) centuries, but also the leap into 13<<2 electronic " +
                "\ntypesetting, remaining 30>>>3 essentially ~6&9|(3&4) unchanged. It was popularised in " +
                "\nthe 5|(1&2&(3|(4&(25^5|6&47)|3)|2)|1) with the release of Letraset sheets containing " +
                "\nLorem Ipsum passages, and more recently with desktop publishing software like Aldus " +
                "\nPageMaker including versions of Lorem Ipsum.\n" +
                "\tIt is a long established fact that a reader will be distracted by the readable " +
                "\ncontent of a page when looking at its layout. The point of using " +
                "\n(~71&(2&3|(3|(2&1>>2|2)&2)|10&2))|78 Ipsum is that it has a more-or-less normal " +
                "\ndistribution of letters, as opposed to using (Content here), content here', making it look " +
                "\nlike readable English.\n" +
                "\tIt is a (8^5|1&2<<(2|5>>2&71))|1200 established fact that a reader will be of a " +
                "\npage when looking at its layout.\n" +
                "\tBye.\n";
        LeafParser leafParser = new LeafParser();
        WordExpressionParser wordExpressionParser = new WordExpressionParser(leafParser);
        LexemParser lexemParser = new LexemParser(wordExpressionParser);
        SentenceParser sentenceParser = new SentenceParser(lexemParser);
        ParagraphParser paragraphParser = new ParagraphParser(sentenceParser);
        Text text1 = new Text();
        text1.addAll(paragraphParser.handleParser(text));
        System.out.println(text1.toString());
    }
}
