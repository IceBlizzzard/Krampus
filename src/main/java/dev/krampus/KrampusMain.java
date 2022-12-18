package dev.krampus;

import dev.krampus.commands.CommandManager;
import dev.krampus.console.Console;
import dev.krampus.utils.PropertyFileUtility;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.swing.*;

public class KrampusMain {

    private static CommandManager commandManager;
    public static JDA jda;
    private static Console console;

    public static void main(String[] args)throws Exception {
        PropertyFileUtility.loadPropertiesFile();
        commandManager = new CommandManager();
        commandManager.initCommands();
        jda  = JDABuilder.createDefault(PropertyFileUtility.getToken()).enableIntents(GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_PRESENCES, GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_MEMBERS).build();
        SwingUtilities.invokeLater(() -> {
            console = new Console();
            console.create();

            String logo = "⠀⠀⠀⠀⠀⠀⠀⢀⣴⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠰⣆⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                    "⠀⠀⠀⠀⠀⠀⡴⠋⡏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡇⠳⣄⠀⠀⠀⠀⠀⠀\n" +
                    "⠀⠀⠀⠀⢀⡾⠁⢰⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣷⠀⠹⣆⠀⠀⠀⠀⠀\n" +
                    "⠀⠀⠀⢀⣾⠁⠀⢸⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⠀⠀⢹⣇⠀⠀⠀⠀\n" +
                    "⠀⠀⠀⣸⡇⠀⠀⠀⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡟⠀⠀⠀⣿⡄⠀⠀⠀\n" +
                    "⠀⠀⠀⣿⠃⠀⠀⠀⢻⡀⠀⠀⠀⢀⣠⣴⡶⠶⠿⠛⠛⠛⠛⠛⠛⠛⠻⠶⣶⣤⣄⡀⠀⠀⠀⢰⠇⠀⠀⠀⢹⡇⠀⠀⠀\t" + "Krampus is now online!" + "\n" +
                    "⠀⠀⠀⣿⡆⠀⠀⠀⠈⠳⣀⣠⣾⣿⡉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠻⣿⣿⣦⣀⡠⠏⠀⠀⠀⠀⣸⡇⠀⠀⠀\n" +
                    "⠀⠀⠀⢻⣇⠀⠀⠀⠀⠀⠀⠹⣿⠙⣷⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢻⣿⣿⠏⠀⠀⠀⠀⠀⠀⣿⠇⠀⠀⠀\t" + "Type 'khelp' for a list of commands" + "\n" +
                    "⣶⠀⠀⠘⣿⡄⠀⠀⠀⠀⠀⠀⣿⡄⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣾⣿⡟⠀⠀⠀⠀⠀⠀⣼⡟⠀⠀⢀⡆\n" +
                    "⣿⣧⡀⠀⠹⣿⡄⠀⠀⠀⠀⠀⣿⡇⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⣤⣴⣿⣿⣿⡇⠀⠀⠀⠀⠀⣴⡿⠁⠀⣠⣿⡇\t" + "Type 'kspam' to mass spam all channels" + "\n" +
                    "⣿⢻⣷⣄⠀⠙⣿⣆⡀⠀⠀⣰⣿⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⠛⠛⠛⠋⠁⠘⣿⣿⣧⠀⠀⠀⢀⣾⡟⠁⢀⣴⣿⢻⡇\n" +
                    "⢹⡇⠙⣿⣷⣄⢸⣿⣿⣿⡿⠟⠁⠀⠀⠀⠀⢀⠀⠀⠀⠀⠀⠀⠀⠀⢤⠀⠀⠀⠈⠻⠿⣷⣶⣶⣿⣿⠀⣰⣿⡿⠁⢸⠁\t" + "Type 'ksanta' to spam a funny santa picture" + "\n" +
                    "⢸⣇⠀⠈⠻⣿⣾⣿⣿⡿⠀⠀⠀⠀⠀⠀⢠⠟⠀⠀⠀⠀⠀⠀⠀⠀⠈⢧⡀⠀⠀⠀⠀⠀⠀⠘⣿⣿⣾⡿⠋⠀⠀⣾⠀\n" +
                    "⠀⣿⡄⠀⠀⠈⣻⣿⣿⣷⠀⠀⠀⠀⣠⠔⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠑⠤⣄⠀⠀⠀⠀⠀⢿⣿⡏⠀⠀⠀⢀⡏⠀\t" + "Type 'kdchannels' to delete all channels" + "\n" +
                    "⠀⠘⣷⣄⠀⠀⣿⣿⣿⣿⣆⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⡀⠀⠸⣿⡇⠀⠀⢀⡼⠁⠀\n" +
                    "⠀⠀⠈⠻⣷⣄⣸⣿⣿⣿⣿⣿⣷⣦⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣤⣾⠋⠉⠀⠀⠀⣿⡇⢀⡴⠋⠀⠀⠀\t" + "Type 'kban' to mass ban everyone" + "\n" +
                    "⠀⠀⠀⠀⠀⠙⢿⣿⣿⣿⣿⣿⣿⣿⣎⠙⠦⣀⠀⠀⣠⠀⠀⢀⣆⠀⠀⣠⠖⢉⣼⠏⠀⠀⠀⠀⠀⣿⠗⠋⠀⠀⠀⠀⠀\n" +
                    "⠀⠀⠀⠀⠀⠀⠈⣿⣿⣿⡿⠛⠛⠛⠛⠶⠶⠛⠉⠻⣿⡀⠀⢸⡿⠋⠉⠳⠶⠞⠋⠀⠀⠀⠀⠀⢰⡟⠀⠀⠀⠀⠀⠀⠀\t" + "Type 'kroulette' to start a game of russian roulette" + "\n" +
                    "⠀⠀⠀⠀⠀⠀⠀⠘⣿⣿⣷⠀⠀⠀⠀⠀⠀⠀⠀⠀⢿⡇⠀⣾⣧⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⡿⠁⠀⠀⠀⠀⠀⠀⠀\n" +
                    "⠀⠀⠀⠀⠀⠀⠀⠀⠀⢹⣿⡀⠀⠀⠀⠀⠀⠀⣀⡀⠘⣇⠀⣿⣿⣄⡀⠀⠀⠀⠀⠀⠀⢠⣾⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀\t" + "Type 'kping' to ping everyone" + "\n" +
                    "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣷⣤⣀⣀⣠⡤⢾⣇⡀⠀⠀⠀⢿⣿⣿⣿⠶⣤⣀⣀⣠⣴⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                    "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢹⣿⣿⣿⣿⣿⣄⠀⠉⠛⡆⠀⠀⢸⡟⠛⠁⢀⣼⣿⣿⣿⣿⣿⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\t" + "Type 'kdroles' to delete all roles a server has" + "\n" +
                    "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠸⣿⣷⡉⠉⠘⣧⡳⣄⣀⣙⠲⠶⠞⢁⣀⡴⣫⡾⠈⠋⣰⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                    "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢻⣿⣷⡄⠀⢷⠹⣶⠯⣭⣍⣉⣭⡭⢷⣼⢡⠇⠀⣰⣿⣿⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\t" + "Type 'kusers' to get a list of every user" + '\n' +
                    "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢻⣿⣿⡀⠈⢧⣝⣀⣀⣀⣀⣀⣀⣘⣡⠞⠀⢸⣿⣿⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                    "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢻⣿⡇⠀⠀⣀⣬⣭⣭⣭⣭⣭⣄⡀⠀⠀⣾⣿⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\t" + "Type 'kcolor' to change the color of the output text" +'\n' +
                    "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠹⣷⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣰⡿⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                    "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠻⣦⣀⡀⠀⢀⣀⠀⠀⢀⣠⡴⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\t" + "Type 'kexit' to exit!" + '\n' +
                    "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠛⠿⢿⣿⣿⣿⠿⠟⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                    "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣀⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀\t" + "\t \tCheck " + System.getProperty("user.home") + "\\Krampus for the dataConfig.properties file" + '\n';

            console.write(logo);
        });
    }

    public static CommandManager getCommandManager() {
        return commandManager;
    }

    public static JDA getJda() {
        return jda;
    }

    public static Console getConsole() {
        return console;
    }
}
