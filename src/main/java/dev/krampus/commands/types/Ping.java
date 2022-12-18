package dev.krampus.commands.types;

import dev.krampus.KrampusMain;
import dev.krampus.commands.AbstractCommand;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;

public class Ping extends AbstractCommand {


    public Ping() {

    }

    @Override
    public String getName() {
        return "Kping";
    }

    @Override
    public void execute() {
        if(KrampusMain.getJda().getTextChannels().size() == 0){
            KrampusMain.getConsole().write("\nThere are no channels to ping users in!");
            return;
        }
        KrampusMain.getJda().getGuilds().forEach(guild -> {
            guild.loadMembers().onSuccess(members -> {
                members.forEach(member -> {
                    for(TextChannel textChannel : guild.getTextChannels()){
                        textChannel.sendMessage("<@" + member.getId() + ">").queue();
                        KrampusMain.getConsole().write("\nTagged " + member.getUser().getName() + " in every channel");
                    }
                });
            });
        });
    }
}
