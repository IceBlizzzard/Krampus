package dev.krampus.utils;

import java.io.*;
import java.util.Arrays;
import java.util.Properties;

public class PropertyFileUtility {

    public static void loadPropertiesFile() {
        File directory = new File(System.getProperty("user.home") + "\\Krampus");
        File file = null;
        if(directory.exists() && directory.isDirectory()){
            file = new File(directory + "\\dataConfig.properties");
        }else{
            directory = new File(System.getProperty("user.home") + "\\Krampus");
            if(directory.mkdir()) {
                System.out.println("Directory Created");
                file =  new File(directory + "\\dataConfig.properties");

            } else {
                System.out.println("Directory is not created");
            }
        }

        if(file.exists()){
            return;
        }
        Properties properties = new Properties();
        try (OutputStream outputStream = new FileOutputStream(file)) {
            properties.setProperty("token", "MTA1MzE4NzM2MjM0MTAwMzMxNg.GwUwKB.CLTUyM4H06HpAe-cwfnwXVH1fT9eORzL7AZvnk");
            properties.setProperty("spam-messages", Arrays.toString(new String[]{"message1", "message2", "message3"}));
            properties.setProperty("spam-iterations", String.valueOf(50));
            properties.store(outputStream, null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Properties initFileInput() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(System.getProperty("user.home") + "\\Krampus\\dataConfig.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return properties;
    }

    public static String getToken() {
        return initFileInput().getProperty("token");
    }

    private static String[] getSpamMessages() {
        return initFileInput().getProperty("spam-messages").replace("[", "").replace("]", "").split(", ");
    }

    public static String getRandomMessage() {
        String[] messages = getSpamMessages();
        return messages[(int) (Math.random() * messages.length)];
    }

    public static Integer getIterations() {
        return Integer.parseInt(initFileInput().getProperty("spam-iterations"));
    }
}
