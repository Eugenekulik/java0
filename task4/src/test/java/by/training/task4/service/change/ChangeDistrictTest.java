package by.training.task4.service.change;

import by.training.task4.bean.District;
import by.training.task4.service.ServiceException;
import by.training.task4.service.ServiceFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

public class ChangeDistrictTest {
    @Test
    public void changeNameTest(){
        District district = new District("first");
        ChangeDistrict changeDistrict = ServiceFactory.getInstance().getChangeDistrict();
        changeDistrict.name(district, "Second");
        Assertions.assertEquals("Second", district.getName());
    }

    @Test
    public void changeAreaTest() throws ServiceException {
        List<District> districts = List.of(new District("first"),new District("second"),
                new District("third"),new District("fourth"));
        List<String> area = List.of("10","110.53","-1","0");
        ChangeDistrict changeDistrict = ServiceFactory.getInstance().getChangeDistrict();
        for(int i=0;i<4;i++){
            changeDistrict.area(districts.get(i),area.get(i));
        }
        Assertions.assertArrayEquals(new Double[]{10.0,110.53,100.0,100.0}, districts.stream()
                .map(District::getArea).collect(Collectors.toList()).toArray());

    }
    @Test
    public void changeAreaExceptionTest(){
        Assertions.assertThrows(ServiceException.class,()-> {
            District district = new District("first");
            String area = "b";
            ChangeDistrict changeDistrict = ServiceFactory.getInstance().getChangeDistrict();
            changeDistrict.area(district,area);
        });
        Assertions.assertThrows(ServiceException.class,()-> {
            District district = new District("first");
            String area = ")";
            ChangeDistrict changeDistrict = ServiceFactory.getInstance().getChangeDistrict();
            changeDistrict.area(district,area);
        });

    }
}
