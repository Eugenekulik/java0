package by.training.task5.bean;

public final class RuntimeInfo {
    private RuntimeInfo() { }

    /**
     * Variable which keep state of program.
     */
    private static boolean isWork = true;

    /**
     * Getter for isWork.
     * @return true if program continue execute.
     */
    public static boolean isWork() {
        return isWork;
    }

    /**
     * Setter for isWork.
     * @param isWork boolean
     */
    public static void setIsWork(boolean isWork) {
        RuntimeInfo.isWork = isWork;
    }
}
