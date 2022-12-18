package dev.krampus.commands.types;

import dev.krampus.commands.AbstractCommand;

public class Exit extends AbstractCommand {

    @Override
    public String getName() {
        return "kexit";
    }

    @Override
    public void execute() {
        System.exit(0);
    }
}
