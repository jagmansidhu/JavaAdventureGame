package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {

    GamePanel gamePanel;
    public Tile[] tile;
    public int[][] mapTileNum;

    public TileManager(GamePanel gamePanel) {

        this.gamePanel = gamePanel;

        tile = new Tile[16];
        mapTileNum = new int[gamePanel.maxWorldCol][gamePanel.maxWorldRow];

        getTileImage();
        loadMap("/map/map.txt");
    }

    public void getTileImage() {
        try {

            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Grass.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Mossywall.png"));
            tile[1].collision = true;

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Wasser.png"));
            tile[2].collision = true;

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Band.png"));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Tree.png"));
            tile[4].collision = true;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Scan text file line by line and convert from string into int until loop over
    public void loadMap(String fileName) {
        try {
            InputStream is = getClass().getResourceAsStream(fileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gamePanel.maxWorldCol && row < gamePanel.maxWorldRow) {

                String line = br.readLine();

                while (col < gamePanel.maxWorldCol) {

                    String[] numbers = line.split(" ");
                    //Changing the split String above into integer so we can use to create map
                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gamePanel.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();

        } catch (Exception e) {

        }
    }

    //automates tile placement
    public void draw(Graphics2D g2) {

        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gamePanel.maxWorldCol && worldRow < gamePanel.maxWorldRow) {

            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gamePanel.tileSize;
            int worldY = worldRow * gamePanel.tileSize;
            int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
            int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;

            if (worldX + gamePanel.tileSize > gamePanel.player.worldX - gamePanel.player.screenX &&
                    worldX - gamePanel.tileSize < gamePanel.player.worldX + gamePanel.player.screenX &&
                    worldY + gamePanel.tileSize > gamePanel.player.worldY - gamePanel.player.screenY &&
                    worldY - gamePanel.tileSize < gamePanel.player.worldY + gamePanel.player.screenY) {
                g2.drawImage(tile[tileNum].image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
            }


            worldCol++;
            if (worldCol == gamePanel.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }


    }
}
