package by.training.task4.controller.command.main;

import by.training.task4.bean.Country;
import by.training.task4.bean.ProgramData;
import by.training.task4.controller.command.Command;
import by.training.task4.service.ServiceFactory;
import by.training.task4.view.Messenger;
import by.training.task4.view.Reader;
import by.training.task4.view.ViewFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CreateCountryCommand implements Command {
    private Country country;
    private static final Logger logger = LogManager.getLogger(CreateCountryCommand.class);
    public CreateCountryCommand(Country country){
        this.country = country;
    }
    public void execute(){
        logger.info("create run");
        Reader reader = ViewFactory.getInstance().getReader();
        Messenger messenger = ViewFactory.getInstance().getMessenger();
        messenger.printProperty("command.nameCountry");
        country = ServiceFactory.getInstance().getCreateCountry()
                .createCountry(reader.getString());
        ProgramData.setCountry(country);
        ProgramData.setIsCountryOpen(true);
        logger.info("create successful");
    }
}
