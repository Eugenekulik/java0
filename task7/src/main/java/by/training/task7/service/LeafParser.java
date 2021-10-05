package by.training.task7.service;

import by.training.task7.bean.Leaf;
import by.training.task7.bean.TextComponent;

import java.util.ArrayList;
import java.util.List;

public class LeafParser extends TextParser {
    private String text;
    @Override
    public List<TextComponent> handleParser(String text) {
        List<TextComponent> leaves = new ArrayList<>();
        for (char c:text.toCharArray()) {
            leaves.add(new Leaf(c));
        }
        return leaves;
    }
}
