package by.training.task4.controller.command.country;

import by.training.task4.bean.City;
import by.training.task4.bean.Country;
import by.training.task4.controller.command.Command;
import by.training.task4.service.change.ChangeCity;
import by.training.task4.service.ServiceException;
import by.training.task4.view.Messenger;
import by.training.task4.view.Reader;
import by.training.task4.view.ViewFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class ChangeCityCommand implements Command {
    private static final Logger logger = LogManager.getLogger(ChangeCityCommand.class);
    private Country country;
    public ChangeCityCommand(Country country){
        this.country = country;
    }
    public void execute() throws ServiceException {
        logger.info("Change city run");
        Reader reader = ViewFactory.getInstance().getReader();
        Messenger messenger = ViewFactory.getInstance().getMessenger();
        messenger.printProperty("command.changeCity");
        City city = country.getCity(reader.getString());
        ChangeCity changeCity = new ChangeCity();
        if(city!=null){
            messenger.printProperty("command.changeCity.change");
            try {
                int a = Integer.parseInt(reader.getString());
                switch (a){
                    case 1:
                        messenger.printProperty("command.changeCity.1");
                        String name = reader.getString();
                        changeCity.name(city, name);
                        break;
                    case 2:
                        messenger.printProperty("command.changeCity.2");
                        String population  = reader.getString();
                        changeCity.population(city, population);
                        break;
                    default:
                        messenger.printProperty("command.change.error");
                        logger.info("changes can't be made");
                        return;
                }
            }
            catch (NumberFormatException e){
                throw new ServiceException(e);
            }
        }
        logger.info("Change city successful");
    }
}
