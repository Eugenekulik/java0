package by.training.task4.controller.command.country;

import by.training.task4.bean.Country;
import by.training.task4.bean.District;
import by.training.task4.controller.command.Command;
import by.training.task4.service.Change.ChangeDistrict;
import by.training.task4.service.ServiceException;
import by.training.task4.view.Messenger;
import by.training.task4.view.Reader;
import by.training.task4.view.ViewFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ChangeDistrictCommand implements Command {
    private static final Logger logger = LogManager.getLogger(ChangeDistrictCommand.class);
    private Country country;
    public ChangeDistrictCommand(Country country){
        this.country = country;
    }
    public void execute() throws ServiceException {
        logger.info("Change district run");
        Reader reader = ViewFactory.getInstance().getReader();
        Messenger messenger = ViewFactory.getInstance().getMessenger();
        messenger.printProperty("command.changeDistrict");
        District district = country.getDistrict(reader.getString());
        ChangeDistrict changeDistrict = new ChangeDistrict();
        if(district!=null){
            messenger.printProperty("command.changeDistrict.change");
            try {
                int a = Integer.parseInt(reader.getString());
                switch (a){
                    case 1:
                        messenger.printProperty("command.changeDistrict.1");
                        String name = reader.getString();
                        changeDistrict.name(district, name);
                        break;
                    case 2:
                        messenger.printProperty("command.changeDistrict.2");
                        String area  = reader.getString();
                        changeDistrict.area(district, area);
                        break;
                    case 3:
                        messenger.printProperty("command.changeDistrict.3");
                        String centerName  = reader.getString();
                        if(!changeDistrict.center(district, centerName)){
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
        logger.info("Change district successful");
    }
}
