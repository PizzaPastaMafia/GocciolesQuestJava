package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.security.Key;

public class Player extends Entity{

    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

    public Player(GamePanel gp, KeyHandler keyH) {

        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth/2;
        screenY = gp.screenHeight/2;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {

        worldX = gp.tileSize*23;
        worldY = gp.tileSize*21;
        speed = 4;
        direction = "down";
        idle = true;
    }

    public void getPlayerImage(){

        try{
            up0 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_0.png"));
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
            up3 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_3.png"));
            down0 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_0.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
            down3 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_3.png"));
            left0 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_0.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_3.png"));
            right0 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_0.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));
            right3 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_3.png"));


        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void update() {

        if(keyH.upPressed || keyH.downPressed ||
                keyH.leftPressed || keyH.rightPressed){

            idle = false;

            if(keyH.upPressed){
                direction = "up";
                worldY -= speed;
            }
            if(keyH.downPressed){
                direction = "down";
                worldY += speed;
            }
            if(keyH.leftPressed){
                direction = "left";
                worldX -= speed;
            }
            if(keyH.rightPressed){
                direction = "right";
                worldX += speed;
            }

            spriteCounter++;
            if(spriteCounter > 5) {

                if(spriteNum == 0){
                    spriteNum = 1;
                }else if(spriteNum == 1){
                    spriteNum = 2;
                }else if(spriteNum == 2){
                    spriteNum = 3;
                }else if(spriteNum == 3){
                    spriteNum = 0;
                }

                spriteCounter = 0;
            }
        }else{
            idle = true;
        }


    }

    public void draw(Graphics2D g2){

        //g2.setColor(Color.white);
        //g2.fillRect(x,y, gp.tileSize, gp.tileSize);

        BufferedImage image = null;

        if(idle){
            spriteNum = 0;
        }

        switch (direction) {
            case "up":
                if(spriteNum == 0){
                    image = up0;
                }else if(spriteNum == 1){
                    image = up1;
                }else if(spriteNum == 2){
                    image = up2;
                }else if(spriteNum == 3){
                    image = up3;
                }
                break;

            case "down":
                if(spriteNum == 0){
                    image = down0;
                }else if(spriteNum == 1){
                    image = down1;
                }else if(spriteNum == 2){
                    image = down2;
                }else if(spriteNum == 3){
                    image = down3;
                }
                break;

            case "left":
                if(spriteNum == 0){
                    image = left0;
                }else if(spriteNum == 1){
                    image = left1;
                }else if(spriteNum == 2){
                    image = left2;
                }else if(spriteNum == 3){
                    image = left3;
                }
                break;

            case "right":
                if(spriteNum == 0){
                    image = right0;
                }else if(spriteNum == 1){
                    image = right1;
                }else if(spriteNum == 2){
                    image = right2;
                }else if(spriteNum == 3){
                    image = right3;
                }
                break;
        }
        g2.drawImage(image,screenX,screenY,gp.tileSize,gp.tileSize, null);
    }
}
