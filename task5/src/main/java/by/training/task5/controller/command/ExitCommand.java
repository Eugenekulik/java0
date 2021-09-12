package by.training.task5.controller.command;

import by.training.task5.bean.RuntimeInfo;
import by.training.task5.controller.Runner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ExitCommand implements Command {
    private static final Logger logger = LogManager.getLogger(Runner.class);
    public void execute(){
        RuntimeInfo.setIsWork(false);
    }
}
