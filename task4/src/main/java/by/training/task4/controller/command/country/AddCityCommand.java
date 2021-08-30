package by.training.task4.controller.command.country;

import by.training.task4.bean.City;
import by.training.task4.bean.Country;
import by.training.task4.bean.District;
import by.training.task4.controller.command.Command;
import by.training.task4.controller.command.main.OpenCountryCommand;
import by.training.task4.service.ServiceException;
import by.training.task4.view.Messenger;
import by.training.task4.view.Reader;
import by.training.task4.view.ViewFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AddCityCommand implements Command {
    private  Country country;
    private static final Logger logger = LogManager.getLogger(AddCityCommand.class);

    public AddCityCommand(Country country){
        this.country = country;
    }
    public void execute() throws ServiceException {
        logger.info("Add city run");
        Messenger messenger = ViewFactory.getInstance().getMessenger();
        Reader reader = ViewFactory.getInstance().getReader();
        messenger.printProperty("command.addCity");
        String[] data=reader.getString().split(" ");
        District district;
        City city;
        try {
            switch (data.length) {
                case 0:
                case 1:
                    throw new ServiceException("too little argument");
                case 2:
                    district = country.getDistrict(data[0]);
                    if (district != null) {
                        city = new City(data[1]);
                        district.addCity(city);
                    }
                    break;
                case 3:
                    district = country.getDistrict(data[0]);
                    if (district != null) {
                        city = new City(data[1]);
                        city.setPopulation(Integer.parseInt(data[2]));
                        district.addCity(city);
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
