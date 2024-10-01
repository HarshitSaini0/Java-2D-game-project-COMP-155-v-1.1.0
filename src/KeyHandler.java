import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    public boolean upPressed;
    public boolean downPressed;
    public boolean leftPressed;
    public boolean rightPressed;
    public void keyTyped(KeyEvent e){

    }
    public void keyPressed(KeyEvent e){
        int code = e.getKeyCode();
        if(code==KeyEvent.VK_W){
            upPressed = true;
        }
        else if(code==KeyEvent.VK_A){
            leftPressed=true;
        }
        else if(code==KeyEvent.VK_S){
            downPressed=true;
        }
        else if(code==KeyEvent.VK_D){
            rightPressed=true;
        }
    }
    public void keyReleased(KeyEvent e){
        int code = e.getKeyCode();
        if(code==KeyEvent.VK_W){
            upPressed = false;
        }
        else if(code==KeyEvent.VK_A){
            leftPressed=false;
        }
        else if(code==KeyEvent.VK_S){
            downPressed=false;
        }
        else if(code==KeyEvent.VK_D){
            rightPressed=false;
        }
    }
}
