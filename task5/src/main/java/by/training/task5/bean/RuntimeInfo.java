package by.training.task5.bean;

public class RuntimeInfo {
    private static boolean isWork = true;

    public static boolean isWork() {
        return isWork;
    }
    public static void setIsWork(boolean isWork) {
        RuntimeInfo.isWork = isWork;
    }
}
