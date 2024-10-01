import javax.swing.*;
import java.awt.*;

public class GamePannel extends JPanel implements Runnable {
    //
    final int nanoSecondsOneInSecond=1000000000;

    final int originalTileSize = 16;
    final int scale = 3;

    final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;
    //  FPS
    int FPS = 60;


    KeyHandler keyHandler = new KeyHandler();

    Thread gameThread ;


    //Player things
    int playerX=100;
    int playerY=100;
    int playerSpeed = 4;

    public GamePannel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);

    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
//    public void run(){
//        double drawInterval = (double) nanoSecondsOneInSecond /FPS; //0.0167secs
//        double nextDrawTime = System.nanoTime()+drawInterval;
//        while(gameThread!=null){
//
//            update();
//            repaint();
//
//            try {
//                double remainingTime = nextDrawTime - System.nanoTime();
//                remainingTime = remainingTime/nanoSecondsOneInSecond;
//                if(remainingTime<0){
//                    remainingTime=0;
//                }
//                Thread.sleep((long) remainingTime);
//                nextDrawTime+=drawInterval;
//
//            }catch (InterruptedException e){
//                e.printStackTrace();
//            }
//        }
//    }


//    public void run(){
//        double drawInterval = nanoSecondsOneInSecond/FPS;
//        double delta = 0;
//        long lastTime = System.nanoTime();
//        long currentTime;
//        long timer = 0;
//        int drawCount = 0;
//        while(gameThread!=null){
//            currentTime = System.nanoTime();
//            delta += (currentTime-lastTime)/delta;
//            lastTime = currentTime;
//            if (delta >= 1){
//                update();
//                repaint();
//                delta--;
//
//            }
//        }
//    }


    public void run() {
        double drawInterval = nanoSecondsOneInSecond / FPS; // Assuming FPS is defined
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / (double) drawInterval;
            lastTime = currentTime;

            while (delta >= 1) {
                update();
                repaint();
                delta--;
            }

            // Optional: Sleep to limit CPU usage (simple frame limiting)
//            try {
//                Thread.sleep(1); // or calculate the appropriate sleep time
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
    }

    public void update(){
        if(keyHandler.upPressed){
            playerY-=playerSpeed;

        }
        else if(keyHandler.downPressed){
            playerY+=playerSpeed;

        }
        if(keyHandler.leftPressed){
            playerX-=playerSpeed;

        }
        if(keyHandler.rightPressed){
            playerX+=playerSpeed;

        }

    }
    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);

        Graphics2D g2 = (Graphics2D)graphics;//Like we do with (char)1 -> simple parse
        g2.setColor(Color.WHITE);
        g2.fillRect(playerX,playerY,tileSize,tileSize);

        g2.dispose();

    }
}
