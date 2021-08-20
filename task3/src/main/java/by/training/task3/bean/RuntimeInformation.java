package by.training.task3.bean;

public class RuntimeInformation {
    public static boolean getIsWork() {
        return isWork;
    }

    public static void setIsWork(boolean isWork) {
        RuntimeInformation.isWork = isWork;
    }

    private static boolean isWork = true;
    RuntimeInformation(){}
}
