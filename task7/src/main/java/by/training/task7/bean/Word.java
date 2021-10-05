package by.training.task7.bean;

public class Word extends TextComposite{
    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        for (TextComponent tc:components) {
            stringBuilder.append(tc.toString());
        }
        return stringBuilder.toString();
    }
}
