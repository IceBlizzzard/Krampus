package dev.krampus.commands.types;

import dev.krampus.KrampusMain;
import dev.krampus.commands.AbstractCommand;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;

import java.util.concurrent.TimeUnit;

public class Roulette extends AbstractCommand {

    @Override
    public String getName() {
        return "kroulette";
    }

    @Override
    public void execute() {
        for (Guild guild : KrampusMain.getJda().getGuilds()) {
            Member selectedMembers = guild.loadMembers().get().get((int) (Math.random() * guild.loadMembers().get().size()));
            if(selectedMembers.isOwner() || selectedMembers.getUser().isBot()){
                KrampusMain.getConsole().write("\n" + selectedMembers.getUser().getName() + "#" + selectedMembers.getUser().getDiscriminator() + " is a bot or owner, try again!");
                break;
            }
            KrampusMain.getConsole().write("\n" + selectedMembers.getUser().getName() + "#" + selectedMembers.getUser().getDiscriminator() + " is the lucky user!");
            KrampusMain.getConsole().write("\nBanning them!");
            selectedMembers.ban(7, TimeUnit.DAYS).queue();
        }
    }
}
