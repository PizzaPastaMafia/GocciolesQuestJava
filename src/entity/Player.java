package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{

    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;
    int hasKey = 0;

    public Player(GamePanel gp, KeyHandler keyH) {
        super(gp);
        
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        //solidArea = new Rectangle(25,30,30,40);
        solidArea = new Rectangle(25,30,30,40);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {

        worldX = gp.tileSize*23;
        worldY = gp.tileSize*21;
        speed = 2;
        direction = "down";
        idle = true;
    }

    public void getPlayerImage(){

        try{
            up0 = ImageIO.read(getClass().getResourceAsStream("../player/boy_up_0.png"));
            up1 = ImageIO.read(getClass().getResourceAsStream("../player/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("../player/boy_up_2.png"));
            up3 = ImageIO.read(getClass().getResourceAsStream("../player/boy_up_3.png"));
            down0 = ImageIO.read(getClass().getResourceAsStream("../player/boy_down_0.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("../player/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("../player/boy_down_2.png"));
            down3 = ImageIO.read(getClass().getResourceAsStream("../player/boy_down_3.png"));
            left0 = ImageIO.read(getClass().getResourceAsStream("../player/boy_left_0.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("../player/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("../player/boy_left_2.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream("../player/boy_left_3.png"));
            right0 = ImageIO.read(getClass().getResourceAsStream("../player/boy_right_0.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("../player/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("../player/boy_right_2.png"));
            right3 = ImageIO.read(getClass().getResourceAsStream("../player/boy_right_3.png"));


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
            }else if(keyH.downPressed){
                direction = "down";
            }else if(keyH.leftPressed){
                direction = "left";
            }else if(keyH.rightPressed){
                direction = "right";
            }

            // CHECK TILE COLLISION
            collisionOn = false;
            gp.cChecker.checkTile(this);

            // CHECK OBJECT COLLISION
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

            // CHECK NPC COLLISION

            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            
            interactNpc(npcIndex);
            //pickUpObject(objIndex);

            // IF COLLISION IS FALSE, PLAYER CAN MOVE
            if(!collisionOn) {

                switch(direction) {
                    case "up": worldY -= speed; break;
                    case "down": worldY += speed; break;
                    case "left": worldX -= speed; break;
                    case "right": worldX += speed; break;
                }
            }

            spriteCounter++;
            if(spriteCounter > 30/speed) {

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

    public void interactNpc(int i){
        if(i != -1) {
            if(gp.keyH.enterPressed){
                gp.gameState = gp.dialogueState;
                gp.npc[i].speak();
            }
            
        }
        gp.keyH.enterPressed = false;
    }

    public void pickUpObject(int i) {

        if(i != -1) {

            String objectName = gp.obj[i].name;

            switch (objectName) {
                case "Key":
                    gp.playSE(1);
                    hasKey++;
                    gp.obj[i] = null;
                    System.out.println("Key: " + hasKey);
                    break;

                case "Door":
                    gp.playSE(3);
                    if(hasKey > 0) {
                        gp.obj[i] = null;
                        hasKey--;
                    }
                    System.out.println("Key: " + hasKey);
                    break;

                case "Boots":
                    gp.playSE(2);
                    speed+=2;
                    gp.obj[i] = null;
                    break;
            }
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
