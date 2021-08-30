package by.training.task3.controller.command;

import by.training.task3.bean.CommandData;
import by.training.task3.bean.IntegerMatrix;
import by.training.task3.service.GetMatrixFromFile;
import by.training.task3.service.ServiceException;
import by.training.task3.service.ServiceFactory;
import by.training.task3.view.Messenger;
import by.training.task3.view.Reader;
import by.training.task3.view.ViewFactory;

public class MatrixSumCommand implements Command{
    private CommandData commandData;
    public MatrixSumCommand(CommandData commandData){
        this.commandData = commandData;
    };
    public void execute(){
        Messenger messenger = ViewFactory.getInstance().getMessenger();
        Reader reader = ViewFactory.getInstance().getReader();
        try {
            messenger.printProperty("command.matrix.file_path");
            commandData.setMatrix1(new GetMatrixFromFile<Integer>(reader.getString()).createIntegerMatrix());
            messenger.printProperty("command.matrix.file_path");
            commandData.setMatrix2(new GetMatrixFromFile<Integer>(reader.getString()).createIntegerMatrix());
            commandData.setMatrixResult(ServiceFactory.getInstance().getMatrixSum().
                    result((IntegerMatrix) commandData.getMatrix1(), (IntegerMatrix) commandData.getMatrix2()));
            messenger.print(commandData.getMatrixResult().toString());
        }
        catch (ServiceException e){
            messenger.print(e.getMessage());
        }
    }
}
