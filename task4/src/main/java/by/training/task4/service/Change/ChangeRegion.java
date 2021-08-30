package by.training.task4.service.Change;

import by.training.task4.bean.District;
import by.training.task4.bean.Region;
import by.training.task4.service.ServiceException;

public class ChangeRegion {
    public ChangeRegion(){}
    public void name(Region region, String name) throws ServiceException {
        region.setName(name);
    }
    public boolean center(Region region,String name){
        return  region.setRegionCenter(name);
    }
}
