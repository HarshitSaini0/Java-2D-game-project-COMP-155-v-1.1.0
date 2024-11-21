package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity{
    GamePanel gamePanel;
    KeyHandler keyHandler;

    int numberOfKeys = 5;


    public final int screenX;
    public final int screenY;

    public Player(GamePanel gamePanel, KeyHandler keyHandler){
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;

        this.screenX = gamePanel.screenWidth /2 - (gamePanel.tileSize/2);
        this.screenY = gamePanel.screenHeight/2 - (gamePanel.tileSize/2);

        this.solidArea = new Rectangle(10,16,28,28);//x = 8, y = 16,width = 32, height = 32
        this.solidAreaDefaultX = this.solidArea.x;
        this.solidAreaDefaultY = this.solidArea.y;

        this.collisionOn = true;

        this.setDefaultValues();
        this.getPlayerImage();


    }

    public void setDefaultValues(){
        this.worldX = gamePanel.tileSize * 23;
        this.worldY = gamePanel.tileSize * 21;
        this.speed = 6;
        this.direction = "down";
    }

    public void getPlayerImage(){
        try {
        up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_up_1.png")));
        up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_up_2.png")));
        down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_down_1.png")));
        down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_down_2.png")));
        left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_left_1.png")));
        left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_left_2.png")));
        right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_right_1.png")));
        right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_right_2.png")));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void update(){

        if(
                keyHandler.downPressed ||
                keyHandler.leftPressed ||
                keyHandler.rightPressed ||
                keyHandler.upPressed
        ) {
            if (keyHandler.upPressed) {
                this.direction = "up";
            }
            else if (keyHandler.downPressed) {
                this.direction = "down";
            }
           else if (keyHandler.leftPressed) {
                this.direction = "left";
            }
            else if (keyHandler.rightPressed) {
                this.direction = "right";
            }

            // CHECK TILE COLLISION
            this.collisionOn = false;
            gamePanel.collisionChecker.checkTile(this);

            // CHECK OBJECT COLLISION
            int objectIndex = gamePanel.collisionChecker.checkObject(this,true);
            pickUpObject(objectIndex);
            //IF COLLISION IS FALSE SO PLAYER CAN MOVE
            if (!collisionOn){
                switch (this.direction){
                    case "up":
                        this.worldY -= this.speed;
                        break;
                    case "down":
                        this.worldY += this.speed;
                        break;
                    case "left":
                        this.worldX -= this.speed;
                        break;
                    case "right":
                        this.worldX += this.speed;
                        break;
                }
            }

            spriteCounter++;

            if (spriteCounter > (gamePanel.FPS / 10) * 2) {
                if (this.spriteNum == 1) {
                    this.spriteNum = 2;
                } else if (this.spriteNum == 2) {
                    this.spriteNum = 1;
                }
                this.spriteCounter = 0;
            }
        }
    }

    public  void pickUpObject(int i){
        if ( i != 999){
            String objectName = gamePanel.obj[i].name;
            switch (objectName){
                case "key":
                    this.numberOfKeys++;
                    gamePanel.obj[i]=null;
                    break;

                case "door":
                    if(numberOfKeys > 0){
                        gamePanel.obj[i]=null;
                        this.numberOfKeys--;
                    }else{
                        System.out.println("no key");
                    }
                    break;
                case "chest":
                    JLabel label = new JLabel("YOU WON");
                    JPanel panel = new JPanel();
                    JFrame winnerFrame = new JFrame("YOU WON");
                    panel.add(label);
                    winnerFrame.add(panel);
                    winnerFrame.setSize(600,100);
                    winnerFrame.setVisible(true);
                    this.speed = 0;
                    break;

            }
        }

    }



    public void draw(Graphics2D g2){
//        g2.setColor(Color.WHITE);
//        g2.fillRect(x,y, gamePanel.tileSize,gamePanel.tileSize);
        BufferedImage image = null;
        switch (this.direction){
            case "up":
                if(this.spriteNum == 1){
                    image = up1;
                    break;
                } else if (this.spriteNum == 2){
                    image = up2;
                    break;
                }


            case "down":
                if(this.spriteNum == 1){
                    image = down1;
                    break;
                } else if (this.spriteNum == 2){
                    image = down2;
                    break;
                }
            case "left":
                if(this.spriteNum == 1){
                    image = left1;
                    break;
                } else if (this.spriteNum == 2){
                    image = left2;
                    break;
                }
            case "right":
                if(this.spriteNum == 1){
                    image = right1;
                    break;
                } else if (this.spriteNum == 2){
                    image = right2;
                    break;
                }
        }
        g2.drawImage(image,screenX,screenY, gamePanel.tileSize, gamePanel.tileSize,null);

    }
}
