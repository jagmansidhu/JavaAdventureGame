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

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;

        setDefaultVals();
        getPlayerImg();
    }

    public void setDefaultVals() {
        x = 100;
        y = 100;
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
                keyHandler.rightPressed ) {
            //Adding so we can swap img at each step to make character look like its moving
            spriteCount++;
            //player animation thanges every 12 frames
            if (spriteCount > 12) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCount = 0;
            }
        }

        if (keyHandler.upPressed) {
            direction = "up";
            y -= speed;
        }
        if (keyHandler.downPressed) {
            direction = "down";
            y += speed;
        }
        if (keyHandler.leftPressed) {
            direction = "left";
            x -= speed;
        }
        if (keyHandler.rightPressed) {
            direction = "right";
            x += speed;
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
        g2.drawImage(image, x, y, gamePanel.tileSize, gamePanel.tileSize, null);
    }


}
