package com.andersenlab.entity_manager_spring.command;

public class HelpCommand extends AbstractCommand {
    public AbstractCommand execute() throws Exception {
        result = "Acceptable command'entity format is: 'action' 'entity' [params]\n"
            + "Available commands are create|remove|show\n"
            + "Available entities are customer|product|purchase\n";
        return this;
    }
}
