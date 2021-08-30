package by.training.task4.controller.command.country;

import by.training.task4.bean.Country;
import by.training.task4.bean.District;
import by.training.task4.bean.Region;
import by.training.task4.controller.command.Command;
import by.training.task4.controller.command.main.OpenCountryCommand;
import by.training.task4.service.ServiceException;
import by.training.task4.view.Messenger;
import by.training.task4.view.Reader;
import by.training.task4.view.ViewFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AddDistrictCommand implements Command {
    private static final Logger logger = LogManager.getLogger(AddDistrictCommand.class);
    private Country country;
    public AddDistrictCommand(Country country){
        this.country = country;
    }
    public void execute() throws ServiceException{
        logger.info("Add city run");
        Messenger messenger = ViewFactory.getInstance().getMessenger();
        Reader reader = ViewFactory.getInstance().getReader();
        messenger.printProperty("command.addDistrict");
        String[] data=reader.getString().split(" ");
        Region region;
        District district;
        try {
            switch (data.length) {
                case 0:
                case 1:
                    throw new ServiceException("too little argument");
                case 2:
                    region = country.getRegion(data[0]);
                    if (region != null) {
                        district = new District(data[1]);
                        region.addDistrict(district);
                    }
                    break;
                case 3:
                    region = country.getRegion(data[0]);
                    if (region != null) {
                        district = new District(data[1]);
                        district.setArea(Double.parseDouble(data[2]));
                        region.addDistrict(district);
                    }
                    break;
                default:
                    throw new ServiceException("too much argument");
            }
        }
        catch (Exception e){
            throw new ServiceException(e);
        }

        logger.info("Add city successful");
    }
}
