package by.training.task3.controller.command;

import by.training.task3.bean.CommandData;
import by.training.task3.bean.MyArray;
import by.training.task3.service.GetArrayFromFile;
import by.training.task3.service.ServiceException;
import by.training.task3.service.ServiceFactory;
import by.training.task3.service.arraySort.BubbleSort;
import by.training.task3.service.arraySort.HashTableSort;
import by.training.task3.service.arraySort.ShekerSort;
import by.training.task3.service.arraySort.SimpleChoiceSort;
import by.training.task3.view.Messenger;
import by.training.task3.view.Reader;
import by.training.task3.view.ViewFactory;

public class SortCommand implements Command{
    CommandData commandData;
    public SortCommand(CommandData commandData){
        this.commandData=commandData;
    }
    @Override
    public void execute(){
        Messenger messenger = ViewFactory.getInstance().getMessenger();
        Reader reader = ViewFactory.getInstance().getReader();
        messenger.printProperty("command.sort.variation");
        commandData.setVariation(SortVariation.valueOf(reader.getString()));
        messenger.printProperty("command.sort.file_path");

        try {
            GetArrayFromFile getArrayFromFile = new GetArrayFromFile(reader.getString());
            MyArray<Integer> array = getArrayFromFile.create(Integer.class);
            commandData.setArray(array);
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
            messenger.print(commandData.getArray().toString());
        }

    }
}
