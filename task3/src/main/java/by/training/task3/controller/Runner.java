package by.training.task3.controller;


import by.training.task3.bean.MyArray;
import by.training.task3.service.arraySort.BubbleSort;

import java.util.ArrayList;
import java.util.Random;

public class Runner {
    public static void main(String []args)  {
        MyArray<Integer> array = new MyArray<>(Integer.class, 40);
        for(int i=0;i<array.getSize();i++){
            array.set(i, (int)(Math.random()*100));
        }
        BubbleSort bubbleSort = new BubbleSort();
        System.out.println(array.toString());

        bubbleSort.sort(array);
        System.out.println(array.toString());
    }
}
