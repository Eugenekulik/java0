package by.training.task7.bean;

import java.net.PortUnreachableException;

public class Lexem extends TextComposite{
    private String delimiter = "";

    public void setDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }
    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        for (TextComponent tc:components) {
            stringBuilder.append(tc.toString());
        }
        stringBuilder.append(delimiter);
        return stringBuilder.toString();
    }
    @Override
    public int size(){
        int size = 0;
        for (TextComponent tc:components) {
            size+=tc.size();
        }
        size+=delimiter.length();
        return size;
    }
}
