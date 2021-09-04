package by.training.task4.service.change;

import by.training.task4.bean.City;
import by.training.task4.service.ServiceException;
import by.training.task4.service.ServiceFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ChangeCityTest {

    @Test
    public void changeNameTest(){
        City city = new City("First");
        ChangeCity changeCity = ServiceFactory.getInstance().getChangeCity();
        changeCity.name(city, "Second");
        Assertions.assertEquals("Second", city.getName());
    }

    @Test
    public void changePopulationTest() throws ServiceException{
        List<City>  cities = List.of(new City("first"),new City("second"),
                new City("third"),new City("fourth"));
        List<String> population = List.of("10","100000","1","-3000");
        ChangeCity changeCity = ServiceFactory.getInstance().getChangeCity();
        for(int i=0;i<4;i++){
            changeCity.population(cities.get(i),population.get(i));
        }
        List<Integer> i = List.of(10,10000,1,0);
        Assertions.assertArrayEquals(new Integer[]{10,100000,1,0}, cities.stream()
                .map(City::getPopulation).collect(Collectors.toList()).toArray());

    }
    @Test
    public void changePopulationExceptionTest() throws ServiceException{
        Assertions.assertThrows(ServiceException.class,()-> {
            City city = new City("first");
            String population = "b";
            ChangeCity changeCity = ServiceFactory.getInstance().getChangeCity();
            changeCity.population(city,population);
        });
        Assertions.assertThrows(ServiceException.class,()-> {
            City city = new City("first");
            String population = "10.5";
            ChangeCity changeCity = ServiceFactory.getInstance().getChangeCity();
            changeCity.population(city,population);
        });
        Assertions.assertThrows(ServiceException.class,()-> {
            City city = new City("first");
            String population = "-";
            ChangeCity changeCity = ServiceFactory.getInstance().getChangeCity();
            changeCity.population(city,population);
        });
    }
}
