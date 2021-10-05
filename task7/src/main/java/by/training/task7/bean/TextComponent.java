package by.training.task7.bean;

public interface TextComponent {
    void add(TextComponent textComponent) throws TextComponentException;
    void remove(TextComponent textComponent) throws TextComponentException;
    Object getChild(int index);
}
