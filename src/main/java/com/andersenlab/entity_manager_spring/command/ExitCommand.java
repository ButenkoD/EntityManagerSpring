package com.andersenlab.entity_manager_spring.command;

import com.andersenlab.entity_manager_spring.ConsoleApplication;

public class ExitCommand extends AbstractCommand {
    public AbstractCommand execute() throws Exception {
        result = "Bye!";
        ConsoleApplication.exitApplication();
        return this;
    }
}
