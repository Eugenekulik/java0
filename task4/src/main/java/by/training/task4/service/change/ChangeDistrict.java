package by.training.task4.service.change;

import by.training.task4.bean.District;
import by.training.task4.service.ServiceException;

public class ChangeDistrict {
    public ChangeDistrict(){}
    public void name(District district, String name) throws ServiceException {
            district.setName(name);
    }
    public void area(District district, String area) throws ServiceException {
        try {
            district.setArea(Double.parseDouble(area));
        }
        catch (NumberFormatException e){
            throw new ServiceException(e);
        }
    }
    public boolean center(District district,String name){
        return district.setDistrictCenter(name);
    }
}
