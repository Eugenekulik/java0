package by.training.task1.service;

public class TimeConverter {
    private TimeConverter(){}

    public static String numToTime(int num)
    {
        int hours = num/3600;
        int minutes = (num - hours*3600)/60;
        int seconds = num - hours*3600 - minutes*60;
        return hours + "ч " + minutes + "м " + seconds + "сек";
    }
}
