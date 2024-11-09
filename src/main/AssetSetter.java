package main;

import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_Key;

public class AssetSetter {

    public GamePannel gamePannel;

    AssetSetter(GamePannel gamePannel){
        this.gamePannel = gamePannel;
    }

    public void setObject(){
        gamePannel.obj[0]=new OBJ_Key();
        gamePannel.obj[0].worldX= 23 * gamePannel.tileSize;
        gamePannel.obj[0].worldY= 7 * gamePannel.tileSize;

        gamePannel.obj[1]=new OBJ_Key();
        gamePannel.obj[1].worldX= 23 * gamePannel.tileSize;
        gamePannel.obj[1].worldY= 40 * gamePannel.tileSize;

        gamePannel.obj[2]=new OBJ_Key();
        gamePannel.obj[2].worldX= 37 * gamePannel.tileSize;
        gamePannel.obj[2].worldY= 9 * gamePannel.tileSize;

        gamePannel.obj[3]=new OBJ_Door();
        gamePannel.obj[3].worldX= 10 * gamePannel.tileSize;
        gamePannel.obj[3].worldY= 11 * gamePannel.tileSize;

        gamePannel.obj[4]=new OBJ_Door();
        gamePannel.obj[4].worldX= 8 * gamePannel.tileSize;
        gamePannel.obj[4].worldY= 28 * gamePannel.tileSize;

        gamePannel.obj[5]=new OBJ_Door();
        gamePannel.obj[5].worldX= 12 * gamePannel.tileSize;
        gamePannel.obj[5].worldY= 22 * gamePannel.tileSize;

        gamePannel.obj[6]=new OBJ_Chest();
        gamePannel.obj[6].worldX= 10 * gamePannel.tileSize;
        gamePannel.obj[6].worldY= 7 * gamePannel.tileSize;
    }

}
