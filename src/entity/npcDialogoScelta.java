package entity;

/*import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;*/

import main.GamePanel;

public class npcDialogoScelta extends npcProva{
    public npcDialogoScelta(GamePanel gp){
        super(gp);

        setAnsware();

        dialoguesWithAnsware = true;

        selected = true;
    }

    public void getImage(){
        super.getImage();
    }

    public void setDialogue(){
        dialogues[0] = "testo 1 ";
        dialogues[1] = null;
        dialogues[2] = null;
        dialogues[3] = null;
        dialogues[4] = null;
    }

    public void setAnsware(){
        for(int i = 0; i < dialogues.length && dialogues[i] != null; i++){
            answare[0][i] = "si";
            answare[1][i] = "no";
        }
    }

    public void setAction(){
        super.setAction();
    }

    public void speak(){
        if(dialogueIndex >= dialogues.length || dialogues[dialogueIndex] == null){
            dialogueIndex = 0;
            endDialogue = true;
            gp.gameState = gp.playState;
            if(!choice){
                System.out.println("stampa cose");
            }
            else{
                System.out.println("non stampare cose");
            }
            return;
        }

        endDialogue = false;
        gp.ui.currentDialogue = dialogues[dialogueIndex];
        gp.ui.endDialogue = endDialogue;
        dialogueIndex++;

        choice = gp.ui.choice;

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

        if(answareIndex >= 4 || dialogues[answareIndex] == null){
            answareIndex = 0;
        }
        
        gp.ui.currentAnsware[0] = answare[0][answareIndex];
        gp.ui.currentAnsware[1] = answare[1][answareIndex];
        gp.ui.answare = true;
        answareIndex++;
    }
}
