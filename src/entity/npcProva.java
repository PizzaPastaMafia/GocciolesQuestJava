package entity;

import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import main.GamePanel;

public class npcProva extends Entity{
    public npcProva(GamePanel gp){
        super(gp);

        direction = "down";
        speed = 1;
        
        getImage();

        setDialogue();
    }

    public void getImage(){

        try{
            up0 = ImageIO.read(getClass().getResourceAsStream("../npc/boy_up_0.png"));
            up1 = ImageIO.read(getClass().getResourceAsStream("../npc/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("../npc/boy_up_2.png"));
            up3 = ImageIO.read(getClass().getResourceAsStream("../npc/boy_up_3.png"));
            down0 = ImageIO.read(getClass().getResourceAsStream("../npc/boy_down_0.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("../npc/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("../npc/boy_down_2.png"));
            down3 = ImageIO.read(getClass().getResourceAsStream("../npc/boy_down_3.png"));
            left0 = ImageIO.read(getClass().getResourceAsStream("../npc/boy_left_0.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("../npc/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("../npc/boy_left_2.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream("../npc/boy_left_3.png"));
            right0 = ImageIO.read(getClass().getResourceAsStream("../npc/boy_right_0.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("../npc/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("../npc/boy_right_2.png"));
            right3 = ImageIO.read(getClass().getResourceAsStream("../npc/boy_right_3.png"));

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void setDialogue(){
        dialogues[0] = "testo 1 ";
        dialogues[1] = "testo 2 ";
        dialogues[2] = "testo 3 ";
        dialogues[3] = "testo 4 ";
        dialogues[4] = "testo 5 ";
    }

    public void setAction(){
        actionLockCounter++;
        
        if(actionLockCounter < 200){
            return;
        }

        actionLockCounter = 0;

        Random random = new Random();
        int i = random.nextInt(4)+1;

        switch(i){
            case 1:
                direction = "up";
            break;
            case 2:
                direction = "down";
            break;
            case 3:
                direction = "right";
            break;
            case 4:
                direction = "left";
            break;
        }
    }

    public void speak(){
        super.speak();
    }
}
