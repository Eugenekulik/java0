package by.training.task3.service.arraySort;

public class SortImplementation {
    private final static HashTableSort hashTableSort= new HashTableSort();
    private final static SimpleChoiceSort simpleChoiceSort = new SimpleChoiceSort();
    private final static ShekerSort shekerSort = new ShekerSort();
    private final static BubbleSort bubbleSort = new BubbleSort();
    public SortImplementation(){};

    public HashTableSort getHashTableSort() {
        return hashTableSort;
    }

    public SimpleChoiceSort getSimpleChoiceSort() {
        return simpleChoiceSort;
    }

    public ShekerSort getShekerSort() {
        return shekerSort;
    }

    public BubbleSort getBubbleSort() {
        return bubbleSort;
    }


}
