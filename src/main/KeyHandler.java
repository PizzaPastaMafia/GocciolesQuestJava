package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
    GamePanel gp;

    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed, arrowLeftPressed, arrowRightPressed;

    public KeyHandler(GamePanel gp){
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();

        if(gp.gameState == gp.playState){
            if(code == KeyEvent.VK_W) {
                upPressed = true;
            }
            else if(code == KeyEvent.VK_S) {
                downPressed = true;
            }
            else if(code == KeyEvent.VK_A) {
                leftPressed = true;
            }
            else if(code == KeyEvent.VK_D) {
                rightPressed = true;
            }
            else if(code == KeyEvent.VK_P) {
                gp.gameState = gp.pauseState;
            }
            else if(code == KeyEvent.VK_ENTER) {
                enterPressed = true;
            }
        } 
        else if(gp.gameState == gp.pauseState){
            if(code == KeyEvent.VK_P) {
                gp.gameState = gp.playState;
            }
        }
        else if(gp.gameState == gp.dialogueState){
            if(code == KeyEvent.VK_ENTER && gp.ui.endDialogue == true) {
                gp.gameState = gp.playState;
            }else if(code == KeyEvent.VK_ENTER){
                enterPressed = true;
            }
            else if(code == KeyEvent.VK_LEFT){
                arrowLeftPressed = true;
            }else if(code == KeyEvent.VK_RIGHT){
                arrowRightPressed = true;
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if(code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if(code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if(code == KeyEvent.VK_D) {
            rightPressed = false;
        }
    }
}
