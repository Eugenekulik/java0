package by.training.task7.bean;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Leaf implements TextComponent{
    private static final Logger logger = LogManager.getLogger(Leaf.class);
    private final char symbol;
    public Leaf(char symbol){
        this.symbol = symbol;
    }
    @Override
    public void add(TextComponent textComponent) throws TextComponentException {
        logger.warn("Leaf -> add. Doing nothing.");
        throw new TextComponentException("Leaf -> add. Doing nothing.");
    }

    @Override
    public void remove(TextComponent textComponent) throws TextComponentException {
        logger.warn("Leaf -> remove. Doing nothing.");
        throw new TextComponentException("Leaf -> remove. Doing nothing.");
    }

    @Override
    public Object getChild(int index) {
        return symbol;
    }
    @Override
    public String toString(){
        return "" + symbol;
    }
}
