package by.training.task3.controller.command;

import by.training.task3.bean.CommandData;
import by.training.task3.bean.MyArray;
import by.training.task3.bean.SortVariation;
import by.training.task3.service.GetArrayFromFile;
import by.training.task3.service.ServiceException;
import by.training.task3.service.ServiceFactory;
import by.training.task3.service.arraySort.*;
import by.training.task3.view.Messenger;
import by.training.task3.view.Reader;
import by.training.task3.view.ViewFactory;

import java.util.ArrayList;

public class SortCommand implements Command{
    CommandData commandData;
    public SortCommand(CommandData commandData){
        this.commandData=commandData;
    }
    @Override
    public void execute(){
        Messenger messenger = ViewFactory.getInstance().getMessenger();
        Reader reader = ViewFactory.getInstance().getReader();
        messenger.print("write variation of sort");
        commandData.setVariation(SortVariation.valueOf(reader.getString()));
        messenger.print("write file path with array");
        MyArray<Integer> array=null;
        try {
            commandData.setArray(new GetArrayFromFile(reader.getString()).create(Integer.class));
        }
        catch (ServiceException e){
            messenger.print(e.getMessage());
        }
        if(commandData.getArray()!=null) {
            switch (commandData.getVariation()) {
                case BUBBLESORT:
                    BubbleSort bubbleSort = ServiceFactory.getInstance().getSortImplementation().getBubbleSort();
                    bubbleSort.sort(commandData.getArray());
                    break;
                case SHEKERSORT:
                    ShekerSort shekerSort = ServiceFactory.getInstance().getSortImplementation().getShekerSort();
                    shekerSort.sort(commandData.getArray());
                    break;
                case SIMPLECHOICESORT:
                    SimpleChoiceSort simpleChoiceSort = ServiceFactory.getInstance().
                            getSortImplementation().getSimpleChoiceSort();
                    simpleChoiceSort.sort(commandData.getArray());
                    break;
                case HASHTABLESORT:
                    HashTableSort hashTableSort = ServiceFactory.getInstance().getSortImplementation().getHashTableSort();
                    hashTableSort.sort(commandData.getArray());
            }
        }
        messenger.print(commandData.getArray().toString());
    }
}
