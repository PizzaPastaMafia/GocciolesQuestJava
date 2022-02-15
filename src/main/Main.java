package main;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {

        JFrame window = new JFrame("Gocciole's Quest");
        Image logo = Toolkit.getDefaultToolkit().getImage("res/logo.png");
        window.setIconImage(logo);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();
        // Causes this Window to be sized to fit the
        // preferred size and layouts of its subcomponents (=GamePanel)

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.setupGame();
        gamePanel.startGameThread();
    }

}
