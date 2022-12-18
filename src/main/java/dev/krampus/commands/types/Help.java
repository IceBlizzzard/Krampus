package dev.krampus.commands.types;

import dev.krampus.KrampusMain;
import dev.krampus.commands.AbstractCommand;
import dev.krampus.console.Console;

public class Help extends AbstractCommand {

    public Help(){

    }

    @Override
    public String getName() {
        return "khelp";
    }

    @Override
    public void execute() {
        Console console = KrampusMain.getConsole();
        console.write("\nKrampus Commands:");
        console.write("\nType 'khelp' for a list of commands!");
        console.write("\nType 'kspam' to mass spam all channels!");
        console.write("\nType 'ksanta' to spam a funny santa picture!");
        console.write("\nType 'kdchannels' to delete all channels!");
        console.write("\nType 'kban' to mass ban everyone!");
        console.write("\nType 'kroulette' to start a game of russian roulette!");
        console.write("\nType 'kping' to attempt to ping every user!");
        console.write("\nType 'kdroles' to delete all roles a server has!");
        console.write("\nType 'kusers' to get a list of every user!");
        console.write("\nType 'kcolor' to change the color of the output text!");
        console.write("\nType 'kexit' to exit!");
    }
}
