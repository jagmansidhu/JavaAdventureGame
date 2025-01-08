package main;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    //Screen Settings
    final int orgTileSize = 16; //16 by 16
    final int scale = 3; // sacles tile size

    final int tileSize = orgTileSize * scale;
    // 4:3 ratio for screen
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;

    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        // improves gahmes rendering performance
        this.setDoubleBuffered(true);

    }
}
