package by.training.task4.controller.command.main;

import by.training.task4.Dao.DaoFactory;
import by.training.task4.bean.Country;
import by.training.task4.bean.ProgramData;
import by.training.task4.controller.command.Command;
import by.training.task4.service.ServiceException;
import by.training.task4.service.ServiceFactory;
import by.training.task4.view.Messenger;
import by.training.task4.view.Reader;
import by.training.task4.view.ViewFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class OpenCountryCommand implements Command {
    private Country country;
    private static final Logger logger = LogManager.getLogger(OpenCountryCommand.class);
    public OpenCountryCommand(Country country){
        this.country = country;
    }
    public void execute() throws ServiceException{
        logger.info("open run");
        Reader reader = ViewFactory.getInstance().getReader();
        Messenger messenger = ViewFactory.getInstance().getMessenger();
        messenger.printProperty("command.filepath");
        country = ServiceFactory.getInstance().getCreateCountry()
                .openCountry(reader.getString());
        ProgramData.setCountry(country);
        ProgramData.setIsCountryOpen(true);
        logger.info("open successful");
    }
}
