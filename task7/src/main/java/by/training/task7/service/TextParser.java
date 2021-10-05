package by.training.task7.service;

import by.training.task7.bean.TextComponent;

import java.util.List;

public abstract class TextParser {
    protected TextParser nextParser = null;
    public abstract List<TextComponent> handleParser(String text);
}
