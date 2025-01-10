package main;

import Entity.Player;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    //SCREEN SETTINGS
    final int orgTileSize = 16; //16 by 16
    final int scale = 3; // sacles tile size

    public final int tileSize = orgTileSize * scale;
    // 4:3 ratio for screen
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    //WORLD SETTINGS
    //size of map.txt
    public final int maxWorldCol = 30;
    public final int maxWorldRow = 24;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;


    final int fps = 60;

    KeyHandler keyHandler = new KeyHandler();
    Thread gameThread; //keeps program running till i stop
    public Player player = new Player(this, keyHandler);
    TileManager tileManager = new TileManager(this);
    public CollisionCheck collisionCheck = new CollisionCheck(this);

    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        // improves gahmes rendering performance
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true); // gamepanel focused to receive input

    }

    public void startGameThread() {

        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000 / fps;
        double nextDrawTime = System.nanoTime() + drawInterval;

        //create game loop core of game
        while (gameThread.isAlive()) {

            // Update Game information
            update();

            // Draw Screen with updated information
            repaint(); // this is how you call paint component

            // this is using sleep method to make sure screen is rendering at a correct speed????
            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;

                if (remainingTime < 0) {
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void update() {
        player.update();
    }

    //use this to paint onto scree Graphics is our paintbrush
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        tileManager.draw(g2);

        player.draw(g2);

        //once drawing above done we dispose to save memory
        g2.dispose();
    }
}
