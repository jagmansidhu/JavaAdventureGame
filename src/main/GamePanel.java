package main;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    //Screen Settings
    final int orgTileSize = 16; //16 by 16
    final int scale = 3; // sacles tile size

    final int tileSize = orgTileSize * scale;
    // 4:3 ratio for screen
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;

    Thread gameThread; //keeps program running till i stop

    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        // improves gahmes rendering performance
        this.setDoubleBuffered(true);

    }

    public void startGameThread(){

        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        //create game loop core of game
        while(gameThread.isAlive()){

            // Update Game information
            update();

            // Draw Screen with updated information
            repaint(); // this is how you call paint component
        }
    }
    public void update(){

    }

    //use this to paint onto scree Graphics is our paintbrush
    public void paintComponent(Graphics g){

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.WHITE);

        g2.fillRect(100, 100, tileSize, tileSize);
        //once drawing above done we dispose to save memory
        g2.dispose();
    }
}
