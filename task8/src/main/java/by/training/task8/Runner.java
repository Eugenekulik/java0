package by.training.task8;


import by.training.task8.bean.Gem;
import by.training.task8.service.GemDomBuilder;
import by.training.task8.service.GemSaxBuilder;

import java.util.Set;

public class Runner {
    public static void main(String[] args){
        GemSaxBuilder gemSaxBuilder = new GemSaxBuilder();
        gemSaxBuilder.buildSetGems("src/main/resources/data/gems.xml");
        Set<Gem> stones = gemSaxBuilder.getGems();
        stones.stream().forEach(System.out::println);
    }
}
