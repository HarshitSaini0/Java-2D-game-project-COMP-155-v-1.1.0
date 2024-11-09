package entity;

import main.GamePannel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity{
    GamePannel gamePannel;
    KeyHandler keyHandler;


    public final int screenX;
    public final int screenY;

    public Player(GamePannel gamePannel,KeyHandler keyHandler){
        this.gamePannel = gamePannel;
        this.keyHandler = keyHandler;

        this.screenX = gamePannel.screenWidth /2 - (gamePannel.tileSize/2);
        this.screenY = gamePannel.screenHeight/2 - (gamePannel.tileSize/2);

        this.solidArea = new Rectangle(10,16,28,28);//x = 8, y = 16,width = 32, height = 32

        this.collisionOn = true;

        this.setDefaultValues();
        this.getPlayerImage();


    }

    public void setDefaultValues(){
        this.worldX = gamePannel.tileSize * 23;
        this.worldY = gamePannel.tileSize * 21;
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
            gamePannel.collisionChecker.checkTile(this);

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

            if (spriteCounter > (gamePannel.FPS / 10) * 2) {
                if (this.spriteNum == 1) {
                    this.spriteNum = 2;
                } else if (this.spriteNum == 2) {
                    this.spriteNum = 1;
                }
                this.spriteCounter = 0;
            }
        }
    }
    public void draw(Graphics2D g2){
//        g2.setColor(Color.WHITE);
//        g2.fillRect(x,y, gamePannel.tileSize,gamePannel.tileSize);
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
        g2.drawImage(image,screenX,screenY,gamePannel.tileSize,gamePannel.tileSize,null);

    }














}
