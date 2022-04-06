package entity;

import main.GamePanel;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    GamePanel gp;

    public int worldX, worldY;
    public int speed;
    public BufferedImage up0,up1,up2,up3,down0,down1,down2,down3,left0,left1,left2,left3,right0,right1,right2,right3;
    public String direction;
    public boolean idle;
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public Rectangle solidArea = new Rectangle(0,0,64,64);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;
    public int actionLockCounter = 0;
    public String[] dialogues = new String[5];
    public String[][] answare = new String[2][5];
    public boolean dialoguesWithAnsware;
    int dialogueIndex = 0;
    int answareIndex = 0;
    public boolean stop;
    public boolean selected;
    public boolean endDialogue;
    public boolean choice;

    public Entity(GamePanel gp){
        this.gp = gp;
        stop = false;
        endDialogue = true;
    }

    public void setAction(){}
    
    public void speak(){
        if(dialogueIndex >= dialogues.length || dialogues[dialogueIndex] == null){
            dialogueIndex = 0;
            endDialogue = true;
            gp.gameState = gp.playState;
            return;
        }

        endDialogue = false;

        gp.ui.currentDialogue = dialogues[dialogueIndex];
        gp.ui.endDialogue = endDialogue;
        gp.ui.answare = false;
        dialogueIndex++;

        switch(gp.player.direction){
            case "up":
                direction = "down";
                break;
            case "down":
                direction = "up";
                break;
            case "left":
                direction = "right";
                break;
            case "right":
                direction = "left";
                break;
        }
    }

    public void update(){
        if(stop == true)
        {
            spriteNum = 0;
            return;
        }
        setAction();

        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkPlayer(this);
        
        if(!collisionOn) {

            switch(direction) {
                case "up": worldY -= speed; break;
                case "down": worldY += speed; break;
                case "left": worldX -= speed; break;
                case "right": worldX += speed; break;
            }
        }
        else{
            actionLockCounter = 201;
        }

        spriteCounter++;
        if(spriteCounter > 40/speed) {

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
    }

    public void draw(Graphics2D g2){
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        BufferedImage image = null;

        // Renders only the tiles in the window
        if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
            
        
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

            g2.drawImage(image,screenX,screenY,gp.tileSize,gp.tileSize,null);
        }
    }
}