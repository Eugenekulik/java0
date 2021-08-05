package by.training.task2.service;

import java.nio.charset.StandardCharsets;

public class CurrentString {
    public CurrentString(){};
    private final String symbols1="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_";
    private final  String  symbols2="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_1234567890";
    public boolean execute(String str){
        boolean current=true;
        if(symbols1.indexOf(str.charAt(0))==-1){
            current=false;
            return current;
        }

        for(int i=1;i<str.length();i++){
            if(symbols2.indexOf(str.charAt(i))==-1){
                current=false;
                return current;
            }
        }

        return current;
    }
}
