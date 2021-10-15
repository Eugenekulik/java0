package by.training.task7.service;


import by.training.task7.bean.Paragraph;
import by.training.task7.bean.Text;
import by.training.task7.bean.TextComponent;
import by.training.task7.bean.TextComposite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;


public class ParagraphSort implements Consumer<TextComponent> {

    @Override
    public void accept(TextComponent textComponent) {
        if(textComponent instanceof Text){
            if(textComponent.getChild(0) instanceof Paragraph) {
                TextComponentComparator textComponentComparator = new TextComponentComparator() {
                    @Override
                    public int compare(TextComponent o1, TextComponent o2) {
                        if(o1 instanceof Paragraph && o2 instanceof Paragraph){
                            return o1.size() - o2.size();
                        }
                        else{
                            throw new UnsupportedOperationException();
                        }
                    }
                };
                textComponent.getChildren().sort(textComponentComparator);
            }
        }
    }
}
