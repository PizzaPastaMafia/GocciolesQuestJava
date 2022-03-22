package main;

import entity.Player;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    // SCREEN SETTINGS
    final int originalTileSize = 16; // All tiles will be in 16x16 pixels
    final int scale = 5; // I'll use a scale because of big screens

    // I'll follow a 4:3 window ratio
    public final int tileSize = originalTileSize * scale; // In this case it'll be 80x80 pixel per tile
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; // Width
    public final int screenHeight = tileSize * maxScreenRow; // Height

    // WORLD SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int maxWorldWidth = tileSize * maxWorldCol;
    public final int maxWorldHeight = tileSize * maxWorldRow;

    // FPS
    int FPS = 144;

    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    public CollisionChecker cChecker = new CollisionChecker(this);
    public Player player = new Player(this,keyH);



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

        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while(gameThread != null) {

            currentTime = System.nanoTime();
            timer += (currentTime - lastTime);
            delta += (currentTime - lastTime) / drawInterval;

            lastTime = currentTime;

            if(delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if(timer >= 1000000000) {
                System.out.println("FPS: "+drawCount);
                drawCount = 0;
                timer = 0;
            }


        }
    }

    public void update() {

        player.update();

    }

    public void paintComponent(Graphics g) {
        // Graphics is a class that has many
        // functions to draw objects on the screen

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
        // Graphics2D has more functions

        tileM.draw(g2);

        player.draw(g2);

        g2.dispose();
    }
}
