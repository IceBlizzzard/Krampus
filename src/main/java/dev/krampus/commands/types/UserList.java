package dev.krampus.commands.types;

import dev.krampus.KrampusMain;
import dev.krampus.commands.AbstractCommand;
import net.dv8tion.jda.api.entities.Guild;

public class UserList extends AbstractCommand {

    @Override
    public String getName() {
        return "kusers";
    }

    @Override
    public void execute() {
        KrampusMain.getConsole().write("\nThere are currently " + KrampusMain.getJda().getUsers().size() + " users in the bot's cache!");
        for(Guild guild : KrampusMain.getJda().getGuilds()){
            guild.loadMembers().get().forEach(member -> {
                KrampusMain.getConsole().write("\n" + member.getUser().getName() + "#" + member.getUser().getDiscriminator());
            });
        }
    }
}
