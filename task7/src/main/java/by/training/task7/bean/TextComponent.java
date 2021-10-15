package by.training.task7.bean;

import java.util.List;

public interface TextComponent {
    void add(TextComponent textComponent) throws TextComponentException;
    void remove(TextComponent textComponent) throws TextComponentException;
    Object getChild(int index);
    int size();
    List<TextComponent> getChildren();
}
