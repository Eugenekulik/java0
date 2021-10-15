package by.training.task7.bean;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TextComposite implements TextComponent {
    protected ArrayList<TextComponent> components = new ArrayList<>();
    @Override
    public void add(TextComponent textComponent) throws TextComponentException {
        components.add(textComponent);
    }
    public void addAll(Collection<TextComponent> newComponents){
        for (TextComponent tc:newComponents) {
            components.add(tc);
        }
    }
    @Override
    public List<TextComponent> getChildren(){
        return components;
    }
    public int size(){
        return components.size();
    }
    @Override
    public void remove(TextComponent textComponent) throws TextComponentException {
        components.remove(textComponent);
    }

    @Override
    public Object getChild(int index) {
        return components.get(index);
    }

}
