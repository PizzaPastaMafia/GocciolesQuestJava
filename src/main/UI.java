package main;

import java.awt.*;
import java.awt.Color;
//import javafx.scene.paint.Color;


public class UI {

    GamePanel gp;
    Graphics2D g2;
    Font arial_40, arial_80B;
    public String currentDialogue = "";
    public String[] currentAnsware = {"", ""};
    String dialogue[] = new String[5];
    public boolean answare;
    public boolean endDialogue;
    public boolean choice;
    public boolean selected;

    public UI(GamePanel gp) {
        this.gp = gp;
        arial_40 = new Font("Arial", Font.PLAIN, 30);
        arial_80B = new Font("Arial", Font.BOLD, 60);
        selected = false;
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;

        g2.setFont(arial_40);
        g2.setColor(Color.WHITE);

        if(gp.gameState == gp.playState){

        }
        else if(gp.gameState == gp.pauseState){
            drawPauseScreen();
        }
        else if(gp.gameState == gp.dialogueState){
            drawDialogueScreen();
        }
    }

    public void drawDialogueScreen(){
        int x = gp.tileSize*2;
        int y = gp.tileSize*8;
        int width = gp.screenWidth - gp.tileSize*4;
        int height =  gp.tileSize*3;

        drawDialogueWindow(x, y,width, height);

        if(answare){
            boolean selected, antiSelected;
            selected = choice;

            antiSelected = !selected;

            drawAnswareWindow(x+gp.tileSize, y+gp.tileSize*2, width-gp.tileSize*9, height-gp.tileSize*2-10, 0, selected);
            drawAnswareWindow(x+gp.tileSize*8, y+gp.tileSize*2, width-gp.tileSize*9, height-gp.tileSize*2-10, 1, antiSelected);
        }

        x = x + gp.tileSize;
        y = y + gp.tileSize;

        for(String line : currentDialogue.split("\n")){
            g2.drawString(line, x, y);
            y+=40;
        }
    }

    public void drawDialogueWindow(int x, int y, int width, int height){
        Color c = new Color(0,0,0,230);
        g2.setColor(c);
        g2.fillRoundRect(x,y, width, height,35,35);
        
        c = new Color(255,255,255);
        
        g2.setColor(c);

        g2.setStroke(new BasicStroke());
        g2.drawRoundRect(x+5,y+5, width-10, height-10,25,25);
    }

    public void drawAnswareWindow(int x, int y, int width, int height, int rispostaNum, boolean selected){
        Color c;
        if(selected){
            c = new Color(91,91,91,100);
        }
        else{
            c = new Color(0,0,0,0);
        }
        
        g2.setColor(c);
        g2.fillRoundRect(x,y, width, height,35,35);
        
        c = new Color(255,255,255);
        
        g2.setColor(c);

        g2.setStroke(new BasicStroke());
        g2.drawRoundRect(x+5,y+5, width-10, height-10,25,25);

        g2.drawString(currentAnsware[rispostaNum], x+100, y+45);
    }

    public void drawPauseScreen(){
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,80F));
        
        String text = "PAUSA";
        int x = getXForCenteredText(text);

        int y = gp.screenHeight;

        g2.drawString(text, x, y);
    }

    public int getXForCenteredText(String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();

        int x = gp.screenHeight/2-length/2;
        return x;
    }
}
