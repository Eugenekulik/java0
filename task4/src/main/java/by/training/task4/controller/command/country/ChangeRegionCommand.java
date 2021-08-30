package by.training.task4.controller.command.country;

import by.training.task4.bean.Country;
import by.training.task4.bean.Region;
import by.training.task4.controller.command.Command;
import by.training.task4.service.Change.ChangeRegion;
import by.training.task4.service.ServiceException;
import by.training.task4.view.Messenger;
import by.training.task4.view.Reader;
import by.training.task4.view.ViewFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ChangeRegionCommand implements Command {
    private static final Logger logger = LogManager.getLogger(ChangeRegionCommand.class);
    private Country country;
    public ChangeRegionCommand(Country country){
        this.country = country;
    }
    public void execute() throws ServiceException {
        logger.info("Change region run");
        Reader reader = ViewFactory.getInstance().getReader();
        Messenger messenger = ViewFactory.getInstance().getMessenger();
        messenger.printProperty("command.changeRegion");
        Region region = country.getRegion(reader.getString());
        ChangeRegion changeRegion = new ChangeRegion();
        if(region!=null){
            messenger.printProperty("command.changeRegion.change");
            try {
                int a = Integer.parseInt(reader.getString());
                switch (a){
                    case 1:
                        messenger.printProperty("command.changeRegion.1");
                        String name = reader.getString();
                        changeRegion.name(region, name);
                        break;
                    case 2:
                        messenger.printProperty("command.changeRegion.2");
                        String centerName  = reader.getString();
                        if(!changeRegion.center(region, centerName)){
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
            }
            catch (NumberFormatException e){
                throw new ServiceException(e);
            }
        }
        logger.info("Change region successful");
    }
}
