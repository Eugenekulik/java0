package by.training.task4.controller.command.country;

import by.training.task4.bean.Country;
import by.training.task4.controller.command.Command;
import by.training.task4.service.Change.ChangeCountry;
import by.training.task4.service.Change.ChangeRegion;
import by.training.task4.service.ServiceException;
import by.training.task4.view.Messenger;
import by.training.task4.view.Reader;
import by.training.task4.view.ViewFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ChangeCountryCommand implements Command {
    private static final Logger logger = LogManager.getLogger(ChangeCountryCommand.class);
    private Country country;

    public ChangeCountryCommand(Country country) {
        this.country = country;
    }

    public void execute() throws ServiceException {
        logger.info("Change country run");
        Reader reader = ViewFactory.getInstance().getReader();
        Messenger messenger = ViewFactory.getInstance().getMessenger();
        ChangeCountry changeCountry = new ChangeCountry();
        messenger.printProperty("command.changeCountry.change");
        try {
            int a = Integer.parseInt(reader.getString());
            switch (a) {
                case 1:
                    messenger.printProperty("command.changeCountry.1");
                    String name = reader.getString();
                    changeCountry.name(country, name);
                    break;
                case 2:
                    messenger.printProperty("command.changeCountry.2");
                    String capital = reader.getString();
                    if (!changeCountry.capital(country, capital)) {
                        messenger.printProperty("command.change.error");
                        logger.info("changes can't be made");
                        return;
                    }
                    break;
                default:
                    messenger.printProperty("command.change.error");
                    logger.info("changes can't be made");
                    return;
            }
        } catch (NumberFormatException e) {
            throw new ServiceException(e);
        }
        logger.info("Change country successful");
    }
}
