package Entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {

    GamePanel gamePanel;
    KeyHandler keyHandler;

    //Indicate where we draw player on the screen in our case keep player in center of screen
    public final int screenX;
    public final int screenY;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;

        screenX = gamePanel.screenWidth / 2 - gamePanel.tileSize / 2;
        screenY = gamePanel.screenHeight / 2;

        bounds = new Rectangle(8, 16, 32, 32);

        setDefaultVals();
        getPlayerImg();
    }

    public void setDefaultVals() {
        //player starting position on map.txt 16 16
        worldX = gamePanel.tileSize * 16;
        worldY = gamePanel.tileSize * 16;
        speed = 4;
        direction = "up";
    }

    // !!! getting player imgs update once we create new Sprites !!!
    public void getPlayerImg() {
        try {
            ;
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/placeholder.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/placeholder2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/placeholder2.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/placeholder.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/placeholder.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/placeholder2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/placeholder.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/placeholder2.png"));


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {


        if (keyHandler.upPressed ||
                keyHandler.downPressed ||
                keyHandler.leftPressed ||
                keyHandler.rightPressed) {
            if (keyHandler.upPressed) {
                direction = "up";
            }
            if (keyHandler.downPressed) {
                direction = "down";
            }
            if (keyHandler.leftPressed) {
                direction = "left";
            }
            if (keyHandler.rightPressed) {
                direction = "right";
            }


            collisionOn = false;
            gamePanel.collisionCheck.checkTile(this);

            if (!collisionOn) {
                switch (direction) {
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            }

            //Adding so we can swap img at each step to make character look like its moving
            spriteCount++;
            //player animation changes every 12 frames
            if (spriteCount > 12) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCount = 0;
            }
        }


    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        switch (direction) {
            case "up":
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
                break;
            case "down":
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
                break;
        }
        g2.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
    }


}
