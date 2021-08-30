package by.training.task4.controller.command.country;

import by.training.task4.bean.Country;
import by.training.task4.controller.command.Command;
import by.training.task4.service.GetInfoUnit;

import by.training.task4.view.Messenger;
import by.training.task4.view.Reader;
import by.training.task4.view.ViewFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GetInfoUnitCommand implements Command {
    private static final Logger logger = LogManager.getLogger(GetInfoUnitCommand.class);
    private  Country country;
    public GetInfoUnitCommand(Country country){
        this.country = country;
    }
    public void execute(){
        logger.info("GetInfoUnit run");
        Messenger messenger = ViewFactory.getInstance().getMessenger();
        Reader reader = ViewFactory.getInstance().getReader();
        messenger.printProperty("command.getInfo");
        GetInfoUnit getInfoUnit = new GetInfoUnit(country);
        messenger.print(getInfoUnit.getInfo(reader.getString()));
        logger.info("GetInfoUnit complete");
    }
}
