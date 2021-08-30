package by.training.task4.bean;

public class ProgramData {
    private static  boolean isWork=true;
    private static boolean isCountryOpen = false;
    private static Country country;

    public ProgramData(){}

    public static  Country getCountry() {
        return country;
    }
    public static void setCountry(Country newCountry) {
        country = newCountry;
    }
    public static boolean isWork() {
        return isWork;
    }
    public static void setWork(boolean work) {
        isWork = work;
    }
    public static boolean isCountryOpen() {
        return isCountryOpen;
    }
    public static void setIsCountryOpen(boolean CountryOpen) {
        isCountryOpen = CountryOpen;
    }
}
