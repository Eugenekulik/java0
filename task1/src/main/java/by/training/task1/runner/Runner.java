package by.training.task1.runner;

import by.training.task1.services.Menu;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.util.Scanner;

public class Runner {
    private static final Logger logger = LogManager.getLogger(Runner.class);

    private Runner() {
    }

    public static void main(String[] args) {
        logger.info("Program start");
        Scanner scanner = new Scanner(System.in);
        int ch =Integer.parseInt(scanner.next());
        Menu.menurun(ch);
    }
}
