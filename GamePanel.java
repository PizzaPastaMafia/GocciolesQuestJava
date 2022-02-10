package main;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    // SCREEN SETTINGS
    final int originalTileSize = 16; // All tiles will be in 16x16 pixels
    final int scale = 3; // I'll use a scale because of big screens

    // I'll follow a 4:3 window ratio
    final int tileSize = originalTileSize * scale; // In this case it'll be 48x48 pixel per tile
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; // Width will be 768 pixels
    final int screenHeight = tileSize * maxScreenRow; // Height will be 576 pixels

    // FPS
    int FPS = 60;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;


    // Set player's default position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);

        // This one is not necessary, but it really increases
        // the quality of the rendered image
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread() {

        gameThread = new Thread(this); // Passing GamePanel to gameThread
        gameThread.start(); // Calling method 'run'
    }

    @Override
    public void run() {

        double drawInterval = 1000000000/FPS; // Every 0.016666sec
        //System.nanoTime();            // 1sec = 1.000.000.000
        //System.currentTimeMillis();   // 1sec = 1.000
        double nextDrawTime = System.nanoTime() + drawInterval;

        while(gameThread != null) {

            update();

            repaint();


            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime /= 1000000;

                if(remainingTime < 0){
                    remainingTime = 0;
                }

                Thread.sleep((long)remainingTime);

                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
    public void update() {

        if(keyH.upPressed){
            playerY -= playerSpeed;
        }
        else if(keyH.downPressed){
            playerY += playerSpeed;
        }
        else if(keyH.leftPressed){
            playerX -= playerSpeed;
        }
        else if(keyH.rightPressed){
            playerX += playerSpeed;
        }

    }
    public void paintComponent(Graphics g) {
        // Graphics is a class that has many
        // functions to draw objects on the screen

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
        // But Graphics2D has more functions

        g2.setColor(Color.white);

        g2.fillRect(playerX,playerY, tileSize, tileSize);

        g2.dispose();
    }
}
