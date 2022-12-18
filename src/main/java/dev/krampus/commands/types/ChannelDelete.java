package dev.krampus.commands.types;

import dev.krampus.KrampusMain;
import dev.krampus.commands.AbstractCommand;
import dev.krampus.utils.DiscordUtils;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;

public class ChannelDelete extends AbstractCommand {

    @Override
    public String getName() {
        return "kdchannels";
    }

    @Override
    public void execute() {
        if(KrampusMain.getJda().getTextChannels().size() == 0){
            KrampusMain.getConsole().write("\nThere are no channels to delete!");
            return;
        }
        for(TextChannel channels : KrampusMain.getJda().getTextChannels()){
            channels.delete().queue();
        }

        DiscordUtils.notifyOfChannels("\nDeleted channels: ");
    }
}
