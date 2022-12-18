package dev.krampus.commands.types;

import dev.krampus.KrampusMain;
import dev.krampus.commands.AbstractCommand;
import dev.krampus.utils.DiscordUtils;
import dev.krampus.utils.PropertyFileUtility;
import net.dv8tion.jda.api.utils.FileUpload;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

public class Santa extends AbstractCommand {

    @Override
    public String getName() {
        return "ksanta";
    }

    @Override
    public void execute() {
        if(KrampusMain.getJda().getTextChannels().size() == 0){
            KrampusMain.getConsole().write("\nThere are no channels to spam this in!");
            return;
        }
        try {
            URL url = new URL("https://i.pinimg.com/originals/4f/8d/35/4f8d350ebcd87fd7cf885ddba93b184d.jpg");
            BufferedImage img = ImageIO.read(url);
            File file = new File("temp.jpg"); // change the '.jpg' to whatever extension the image has
            ImageIO.write(img, "jpg", file); // again, change 'jpg' to the correct extension
            for(int i = 0; i < PropertyFileUtility.getIterations(); i++) {
                KrampusMain.getJda().getTextChannels().forEach(textChannel -> textChannel.sendFiles(FileUpload.fromData(file)).queue());
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        DiscordUtils.notifyOfChannels("\nSending funny santa images to channels ");
    }
}
