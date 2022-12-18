package dev.krampus.console;


import dev.krampus.KrampusMain;
import dev.krampus.commands.AbstractCommand;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.net.URL;
import java.util.Scanner;

public class Console implements Runnable {

    private JFrame consoleFrame;
    private JTextArea outputArea;
    private JTextField inputField;
    PipedOutputStream outputFromField;
    PipedInputStream inputFromField;
    private Scanner fieldInput;
    private PrintStream fieldOutput;

    public void create() {
        //create components
        consoleFrame = new JFrame("Java console");
        outputArea = new JTextArea();
        inputField = new JTextField();
        consoleFrame.setTitle("Krampus");
        try {
            URL url = new URL("https://img.freepik.com/premium-vector/christmas-krampus-character-design_101717-14.jpg");
            BufferedImage img = ImageIO.read(url);
            ImageIcon icon = new ImageIcon(img);
            consoleFrame.setIconImage(icon.getImage());
        }catch (Exception e){
            e.printStackTrace();
        }

        //Make outputArea read-only
        outputArea.setEditable(false);

        outputArea.setBackground(Color.BLACK);
        inputField.setBackground(Color.WHITE);
        outputArea.setForeground(Color.CYAN);
        outputArea.setFont(new Font("Time New Roman", Font.BOLD, 18));
        inputField.setForeground(Color.BLACK);

        //Add components
        consoleFrame.setLayout(new BorderLayout());
        consoleFrame.add(outputArea, BorderLayout.CENTER);
        consoleFrame.add(inputField, BorderLayout.SOUTH);
        consoleFrame.add(new JScrollPane(outputArea));
        //Setup Piped IO
        outputFromField = new PipedOutputStream();
        inputFromField = new PipedInputStream();
        try {
            outputFromField.connect(inputFromField);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        fieldInput = new Scanner(inputFromField);
        fieldOutput = new PrintStream(outputFromField);

        //Setup listeners

        //This listener listens for ENTER key on the inputField.
        inputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = inputField.getText();
                fieldOutput.println(text);
                inputField.setText("");
                //Wake up the other thread for an immediate response.
                synchronized (inputFromField) {
                    inputFromField.notify();
                }
            }
        });

        //Setup Frame for display
        consoleFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        consoleFrame.setSize(400, 300);
        consoleFrame.setVisible(true);

        //Start other thread that will run Console.run()
        Thread mainProgram = new Thread(this);
        mainProgram.start();
    }

    public void run() {
        while (fieldInput.hasNextLine()) {
            String line = fieldInput.nextLine();
            for (AbstractCommand command : KrampusMain.getCommandManager().getCommands()) {
                if (line.equalsIgnoreCase(command.getName())) {
                    command.execute();
                }
            }
        }
    }

    public void write(String text) {
        outputArea.append(text);
    }

    public JTextArea getOutputArea() {
        return outputArea;
    }

    public JTextField getInputField() {
        return inputField;
    }

    public PipedInputStream getInputFromField() {
        return inputFromField;
    }
}