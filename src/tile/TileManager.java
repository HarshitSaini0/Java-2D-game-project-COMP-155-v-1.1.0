package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager {
    GamePanel gamePanel;
    public Tile [] tile;
    public int mapTileNum[][];

    public TileManager(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        tile = new Tile[10];
        mapTileNum = new int[gamePanel.maxWorldCol][gamePanel.maxWorldRow];


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


            String [] tilesWithCollision = {
                    "wall",
                    "water",
                    "tree"
            };

            for(int i = 0;i<tiles.length;i++){
                this.tile[i] = new Tile();
                String tilePath = "/tiles/"+tiles[i]+".png";
                this.tile[i].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(tilePath)));
                for( String block : tilesWithCollision){
                    if(block.equals(tiles[i])){
                        this.tile[i].collision = true;
                        break;
                    }
                }
            }


        }
        catch (IOException e){
            e.printStackTrace();
        }
    }


    public void loadMap(String mapFilePath){
        try {
            InputStream is = getClass().getResourceAsStream(mapFilePath);
            assert is != null;
            BufferedReader br = new BufferedReader((new InputStreamReader(is)));

            int col = 0;
            int row = 0;

            while (col < gamePanel.maxWorldCol && row < gamePanel.maxWorldRow){
                String line = br.readLine();
                while(col < gamePanel.maxWorldCol){
                    String[] numbers = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);


                    mapTileNum[col][row] = num;
                    System.out.print(num+" ");
                    col++;


                }
                if(col == gamePanel.maxWorldCol){
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
        while(worldCol < gamePanel.maxWorldCol && worldRow < gamePanel.maxWorldRow){

            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gamePanel.tileSize;
            int worldY = worldRow * gamePanel.tileSize;
            int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
            int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;




            if(
                    worldX + gamePanel.tileSize > gamePanel.player.worldX - gamePanel.player.screenX &&
                    worldX - gamePanel.tileSize < gamePanel.player.worldX + gamePanel.player.screenX &&
                    worldY + gamePanel.tileSize > gamePanel.player.worldY - gamePanel.player.screenY &&
                    worldY - gamePanel.tileSize < gamePanel.player.worldY + gamePanel.player.screenY
            ){
                g2.drawImage(tile[tileNum].image,screenX,screenY, gamePanel.tileSize, gamePanel.tileSize,null);
            }

            worldCol++;

            if(worldCol== gamePanel.maxWorldCol){
                worldCol=0;
                worldRow++;
            }
        }
    }
}
