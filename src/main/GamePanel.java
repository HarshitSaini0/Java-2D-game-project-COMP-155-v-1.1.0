package main;

import entity.Player;
import object.SuperObject;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
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




    public GamePanel() {
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
