package com.andersenlab.entity_manager_spring;

import com.andersenlab.entity_manager_spring.command.AbstractCommand;
import com.andersenlab.entity_manager_spring.command.CommandFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Scanner;

public class ConsoleApplication {
    private static final String CONFIG_LOCATION = "classpath:/spring.xml";
    private static final String WELLCOME_TEXT = "Waiting for the next command...";
    private static final String COMMAND_CHUNKS_DELIMITER = " ";
    private static boolean exitApplication = false;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (!exitApplication) {
            System.out.println(WELLCOME_TEXT);
            String line = scanner.nextLine();
            try {
                AbstractCommand command = CommandFactory.create(line.split(COMMAND_CHUNKS_DELIMITER));
                System.out.println(command.execute().getResult());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void exitApplication() {
        exitApplication = true;
    }

    private static class ContextHolder {
        static ClassPathXmlApplicationContext instance = new ClassPathXmlApplicationContext(CONFIG_LOCATION);
    }

    public static ClassPathXmlApplicationContext getContext() {
        return ContextHolder.instance;
    }
}
