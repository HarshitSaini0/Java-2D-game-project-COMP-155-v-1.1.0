package main;

import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_Key;

public class AssetSetter {

    public GamePanel gamePanel;

    AssetSetter(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }

    public void setObject(){
        gamePanel.obj[0]=new OBJ_Key();
        gamePanel.obj[0].worldX= 23 * gamePanel.tileSize;
        gamePanel.obj[0].worldY= 7 * gamePanel.tileSize;

        gamePanel.obj[1]=new OBJ_Key();
        gamePanel.obj[1].worldX= 23 * gamePanel.tileSize;
        gamePanel.obj[1].worldY= 40 * gamePanel.tileSize;

        gamePanel.obj[2]=new OBJ_Key();
        gamePanel.obj[2].worldX= 37 * gamePanel.tileSize;
        gamePanel.obj[2].worldY= 9 * gamePanel.tileSize;

        gamePanel.obj[3]=new OBJ_Door();
        gamePanel.obj[3].worldX= 10 * gamePanel.tileSize;
        gamePanel.obj[3].worldY= 11 * gamePanel.tileSize;

        gamePanel.obj[4]=new OBJ_Door();
        gamePanel.obj[4].worldX= 8 * gamePanel.tileSize;
        gamePanel.obj[4].worldY= 28 * gamePanel.tileSize;

        gamePanel.obj[5]=new OBJ_Door();
        gamePanel.obj[5].worldX= 12 * gamePanel.tileSize;
        gamePanel.obj[5].worldY= 22 * gamePanel.tileSize;

        gamePanel.obj[6]=new OBJ_Chest();
        gamePanel.obj[6].worldX= 10 * gamePanel.tileSize;
        gamePanel.obj[6].worldY= 7 * gamePanel.tileSize;
    }

}
