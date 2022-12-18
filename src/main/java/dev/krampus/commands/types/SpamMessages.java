package dev.krampus.commands.types;

import dev.krampus.KrampusMain;
import dev.krampus.commands.AbstractCommand;
import dev.krampus.utils.DiscordUtils;
import dev.krampus.utils.PropertyFileUtility;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;

public class SpamMessages extends AbstractCommand {

    public SpamMessages() {

    }

    @Override
    public String getName() {
        return "kspam";
    }

    @Override
    public void execute() {
        if(KrampusMain.getJda().getTextChannels().size() == 0){
            KrampusMain.getConsole().write("\nThere are no channels to spam this is!");
            return;
        }
        for (int i = 0; i < PropertyFileUtility.getIterations(); i++) {
            for (TextChannel textChannel : KrampusMain.getJda().getTextChannels()) {
                textChannel.sendMessage(PropertyFileUtility.getRandomMessage()).queue();
            }
        }
        DiscordUtils.notifyOfChannels("\nSpamming messages in channels: ");
    }
}
