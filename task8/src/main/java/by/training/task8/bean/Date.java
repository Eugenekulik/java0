package by.training.task8.bean;

import java.util.regex.Pattern;

public class Date {
    private int year;
    private int month;
    private int day;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
    public void createFromPattern(String newDate){
        String[] values = Pattern.compile("-").split(newDate);
        year = Integer.parseInt(values[0]);
        month = Integer.parseInt(values[1]);
        day = Integer.parseInt(values[2]);
    }
    @Override
    public String toString(){
        return year + "-" + month + "-" + day;
    }
}
