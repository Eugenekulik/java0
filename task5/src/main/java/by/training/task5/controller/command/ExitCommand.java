package by.training.task5.controller.command;

import by.training.task5.bean.RuntimeInfo;

/**
 * Class implement interface Command end realize exit the program.
 * @see Command
 * @see RuntimeInfo
 */
public class ExitCommand implements Command {
    /**
     * Method change  field isWork of RuntimeInfo
     * by using which main loop continue work.
     */
    public void execute() {
        RuntimeInfo.setIsWork(false);
    }
}
