package dev.krampus.commands.types;

import dev.krampus.KrampusMain;
import dev.krampus.commands.AbstractCommand;

import javax.swing.*;
import java.awt.*;

public class ColorPick extends AbstractCommand {

    private JColorChooser colorChooser = new JColorChooser();

    @Override
    public String getName() {
        return "kcolor";
    }

    @Override
    public void execute() {
        Color newColor = JColorChooser.showDialog( colorChooser, "Color Chooser", Color.CYAN);
        KrampusMain.getConsole().getOutputArea().setForeground(newColor);
        KrampusMain.getConsole().write("\nYou have changed the color of the console!");
    }
}
