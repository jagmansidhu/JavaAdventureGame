package Entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {

    public int worldX,worldY;
    public int speed;

    //BufferedImage decribes an image with and accessible buffer of image data
    public BufferedImage up1, up2, down1, down2, right1, right2, left1, left2;
    public String direction;

    public int spriteCount = 0;
    public int spriteNum = 1;

    public Rectangle bounds;
    public boolean collisionOn = false;



}
