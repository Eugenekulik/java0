package by.training.task7.bean;

public class Paragraph extends TextComposite{
    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\t");
        for (TextComponent tc:components) {
            stringBuilder.append(tc.toString());
        }
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }
}
