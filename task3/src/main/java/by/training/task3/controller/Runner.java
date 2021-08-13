package by.training.task3.controller;

import by.training.task3.bean.MyMatrix;
import by.training.task3.view.Messenger;

import java.util.ArrayList;

public class Runner {
    public static void main(String []args){
        Messenger messenger=new Messenger();
        ArrayList<Integer> temp= new ArrayList<Integer>();
        temp.add(10);
        temp.add(54);
        temp.add(32);
        temp.add(17);
        temp.add(4);
        temp.add(11);
        temp.add(28);
        temp.add(39);
        temp.add(1);
        MyMatrix<Integer> m=new MyMatrix<Integer>(temp,3,3);
        messenger.print(m.toString());
    }
}
