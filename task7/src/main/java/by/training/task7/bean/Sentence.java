package by.training.task7.bean;

public class Sentence extends TextComposite{
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
}
