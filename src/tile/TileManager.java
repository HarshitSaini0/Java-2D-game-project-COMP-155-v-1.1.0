package tile;

import main.GamePannel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePannel gamePannel ;
    Tile [] tile;
    int mapTileNum[][];

    public TileManager(GamePannel gamePannel){
        this.gamePannel = gamePannel;
        tile = new Tile[10];
        mapTileNum = new int[gamePannel.maxWorldCol][gamePannel.maxWorldRow];


        this.getTileImage();
        this.loadMap("/maps/world01.txt");

    }

    public  void getTileImage(){
        try{

            String [] tiles = {
                    "grass",
                    "wall",
                    "water",
                    "earth",
                    "tree",
                    "sand"
            };

            for(int i = 0;i<tiles.length;i++){
                this.tile[i] = new Tile();
                String tilePath = "/tiles/"+tiles[i]+".png";
                this.tile[i].image = ImageIO.read(getClass().getResourceAsStream(tilePath));
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }


    public void loadMap(String mapFilePath){
        try {
            InputStream is = getClass().getResourceAsStream(mapFilePath);
            BufferedReader br = new BufferedReader((new InputStreamReader(is)));

            int col = 0;
            int row = 0;

            while (col < gamePannel.maxWorldCol && row < gamePannel.maxWorldRow){
                String line = br.readLine();
                while(col < gamePannel.maxWorldCol){
                    String[] numbers = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);


                    mapTileNum[col][row] = num;
                    System.out.print(num+" ");
                    col++;


                }
                if(col == gamePannel.maxWorldCol){
                    col = 0;
                    row++;

                    System.out.println(row);
                }
            }
            br.close();

        }catch (Exception e){

        }
    }

    public  void draw(Graphics2D g2){
        int worldRow = 0;
        int worldCol = 0;
        while(worldCol < gamePannel.maxWorldCol && worldRow < gamePannel.maxWorldRow){

            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gamePannel.tileSize;
            int worldY = worldRow * gamePannel.tileSize;
            int screenX = worldX - gamePannel.player.worldX + gamePannel.player.screenX;
            int screenY = worldY - gamePannel.player.worldY + gamePannel.player.screenY;

            if(
                    worldX + gamePannel.tileSize > gamePannel.player.worldX - gamePannel.player.screenX &&
                    worldX - gamePannel.tileSize < gamePannel.player.worldX + gamePannel.player.screenX &&
                    worldY + gamePannel.tileSize > gamePannel.player.worldY - gamePannel.player.screenY &&
                    worldY - gamePannel.tileSize < gamePannel.player.worldY + gamePannel.player.screenY
            ){
                g2.drawImage(tile[tileNum].image,screenX,screenY,gamePannel.tileSize,gamePannel.tileSize,null);
            }

            worldCol++;

            if(worldCol==gamePannel.maxWorldCol){
                worldCol=0;
                worldRow++;
            }
        }
    }
}
