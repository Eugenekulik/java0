package by.training.task7.service;

import by.training.task7.bean.*;

import java.util.function.Consumer;

public class SentenceSort implements Consumer<TextComponent> {
    @Override
    public void accept(TextComponent textComponent) {
        if(!(textComponent instanceof Sentence)){
            if(textComponent instanceof Leaf){
                return;
            }
            for (TextComponent tc:textComponent.getChildren()) {
                accept(tc);
            }
        }
        TextComponentComparator textComponentComparator = new TextComponentComparator() {
            @Override
            public int compare(TextComponent o1, TextComponent o2) {
                if((o1 instanceof Word && o2 instanceof Word)
                        ||(o1 instanceof Lexem && o2 instanceof Lexem)
                        ||(o1 instanceof Expression && o2 instanceof Expression)) {
                    return o1.size() - o2.size();
                }
                return 0;
            }
        };
        textComponent.getChildren().sort(textComponentComparator);
    }
}
