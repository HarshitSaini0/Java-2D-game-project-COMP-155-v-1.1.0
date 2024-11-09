package main;

import entity.Entity;

public class CollisionChecker {
    GamePannel gamePannel;

    public CollisionChecker(GamePannel gamePannel){
        this.gamePannel = gamePannel;
    }

    public void checkTile(Entity entity){
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX / gamePannel.tileSize;
        int entityRightCol = entityRightWorldX / gamePannel.tileSize;
        int entityTopRow = entityTopWorldY / gamePannel.tileSize ;
        int entityBottomRow = entityBottomWorldY / gamePannel.tileSize ;

        int tileNum1, tileNum2;

        switch (entity.direction){
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed) / gamePannel.tileSize;
                tileNum1 = gamePannel.tileManager.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gamePannel.tileManager.mapTileNum[entityRightCol][entityTopRow];

                if (
                        gamePannel.tileManager.tile[tileNum1].collision ||
                        gamePannel.tileManager.tile[tileNum2].collision
                ){
                    entity.collisionOn = true;
                }

                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed) / gamePannel.tileSize;
                tileNum1 = gamePannel.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gamePannel.tileManager.mapTileNum[entityRightCol][entityBottomRow];

                if (
                        gamePannel.tileManager.tile[tileNum1].collision ||
                                gamePannel.tileManager.tile[tileNum2].collision
                ){
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed) / gamePannel.tileSize;
                tileNum1 = gamePannel.tileManager.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gamePannel.tileManager.mapTileNum[entityLeftCol][entityBottomRow];

                if (
                        gamePannel.tileManager.tile[tileNum1].collision ||
                                gamePannel.tileManager.tile[tileNum2].collision
                ){
                    entity.collisionOn = true;
                }
                break;

            case "right":
                entityRightCol = (entityRightWorldX + entity.speed) / gamePannel.tileSize;
                tileNum1 = gamePannel.tileManager.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gamePannel.tileManager.mapTileNum[entityRightCol][entityBottomRow];

                if (
                        gamePannel.tileManager.tile[tileNum1].collision ||
                                gamePannel.tileManager.tile[tileNum2].collision
                ){
                    entity.collisionOn = true;
                }
                break;
        }

    }

    
}
