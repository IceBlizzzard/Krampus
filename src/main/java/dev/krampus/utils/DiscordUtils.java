package dev.krampus.utils;

import dev.krampus.KrampusMain;

import java.util.LinkedList;
import java.util.List;

public class DiscordUtils {

    public static void notifyOfChannels(String message){
        final List<String> channels = new LinkedList<>();
        KrampusMain.getJda().getTextChannels().forEach(textChannel -> {
            channels.add(textChannel.getName());
        });

        KrampusMain.getConsole().write(message + channels);
        channels.clear();
    }
}
