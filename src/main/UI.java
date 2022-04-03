package main;

import java.awt.*;
import java.awt.Color;
//import javafx.scene.paint.Color;


public class UI {

    GamePanel gp;
    Graphics2D g2;
    Font arial_40, arial_80B;
    public String currentDialogue = "";
    String dialogue[] = new String[20];

    public UI(GamePanel gp) {
        this.gp = gp;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80B = new Font("Arial", Font.BOLD, 80);
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
