package by.training.task4.controller.command;

import by.training.task4.bean.*;
import by.training.task4.controller.command.country.*;
import by.training.task4.controller.command.main.SaveCountryCommand;
import by.training.task4.controller.command.main.CreateCountryCommand;
import by.training.task4.controller.command.main.ExitCommand;
import by.training.task4.controller.command.main.OpenCountryCommand;
import by.training.task4.service.ServiceException;

public class Client {
    Command command = null;
    Country country;
    public Client(Country country){
        this.country = country;
    }
    public Command initCommand(CommandType cmd) throws ServiceException {
        switch (cmd){
            case EXIT:
                command = new ExitCommand();
                break;
            case OPENCOUNTRY:
                if(ProgramData.isCountryOpen()){
                    throw new ServiceException("Can't execute this command");
                }
                command = new OpenCountryCommand(country);
                break;
            case SAVECOUNTRY:
                if(!ProgramData.isCountryOpen()){
                    throw new ServiceException("Can't execute this command");
                }
                command = new SaveCountryCommand(country);
                break;
            case CREATECOUNTRY:
                if(ProgramData.isCountryOpen()){
                    throw new ServiceException("Can't execute this command");
                }
                command = new CreateCountryCommand(country);
                break;
            case ADDCITY:
                if(!ProgramData.isCountryOpen()){
                    throw new ServiceException("Can't execute this command");
                }
                command = new AddCityCommand(country);
                break;
            case ADDDISTRICT:
                if(!ProgramData.isCountryOpen()){
                    throw new ServiceException("Can't execute this command");
                }
                command = new AddDistrictCommand(country);
                break;
            case ADDREGION:
                if(!ProgramData.isCountryOpen()){
                    throw new ServiceException("Can't execute this command");
                }
                command = new AddRegionCommand(country);
                break;
            case CHANGECITY:
                if(!ProgramData.isCountryOpen()){
                    throw new ServiceException("Can't execute this command");
                }
                command = new ChangeCityCommand(country);
                break;
            case CHANGEDISTRICT:
                if(!ProgramData.isCountryOpen()){
                    throw new ServiceException("Can't execute this command");
                }
                command = new ChangeDistrictCommand(country);
                break;
            case CHANGEREGION:
                if(!ProgramData.isCountryOpen()){
                    throw new ServiceException("Can't execute this command");
                }
                command = new ChangeRegionCommand(country);
                break;
            case CHANGECOUNTRY:
                if(!ProgramData.isCountryOpen()){
                    throw new ServiceException("Can't execute this command");
                }
                command = new ChangeCountryCommand(country);
                break;
            case GETINFOUNIT:
                if(!ProgramData.isCountryOpen()){
                    throw new ServiceException("Can't execute this command");
                }
                command = new GetInfoUnitCommand(country);
                break;

        }
        return command;
    }
}
