package main;

import entity.Player;
import object.SuperObject;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePannel extends JPanel implements Runnable {
    // Screen settings
    final int nanoSecondsOneInSecond=1000000000;

    final int originalTileSize = 16;
    final int scale = 3;

    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    //WORLD SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;


    //  FPS
    public int FPS = 30;

    TileManager tileManager = new TileManager(this);
    KeyHandler keyHandler = new KeyHandler();
    Thread gameThread ;
    public CollisionChecker collisionChecker = new CollisionChecker(this);
    public AssetSetter assetSetter = new AssetSetter(this);
    public Player player = new Player(this,keyHandler);
    public SuperObject obj[] =  new SuperObject[10];




    public GamePannel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);

    }

    public void setUpGameObjects(){
        assetSetter.setObject();
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
        int  a = 1;
        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / (double) drawInterval;
            lastTime = currentTime;



            while (delta >= 1) {

                try {
                    update();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
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

    public void update() throws InterruptedException {
        player.update();

    }
    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);

        Graphics2D g2 = (Graphics2D)graphics;//Like we do with (char)1 -> simple parse
        // TILES
        tileManager.draw(g2);

        //OBJECTS
        for (SuperObject superObject : obj) {
            if (superObject != null) {
                superObject.draw(g2, this);
            }
        }

        //PLAYER
        player.draw(g2);

        g2.dispose();

    }
}
