package dev.krampus.commands;

import dev.krampus.commands.types.*;

import java.util.ArrayList;
import java.util.List;

public class CommandManager {

    private List<AbstractCommand> commands = new ArrayList<>();

    public void initCommands(){
        commands.add(new SpamMessages());
        commands.add(new MassBan());
        commands.add(new Help());
        commands.add(new Exit());
        commands.add(new ChannelDelete());
        commands.add(new Ping());
        commands.add(new RolesDelete());
        commands.add(new UserList());
        commands.add(new ColorPick());
        commands.add(new Roulette());
        commands.add(new Santa());
    }

    public List<AbstractCommand> getCommands() {
        return commands;
    }
}
