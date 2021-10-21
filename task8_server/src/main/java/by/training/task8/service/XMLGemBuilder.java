package by.training.task8.service;

import by.training.task8.bean.Gem;

import java.util.Set;

public abstract class XMLGemBuilder {
    public abstract Set<Gem> getGems();
    public abstract void buildSetGems(String filename) throws ServiceException;
}
