package main;

import object.Obj_Boots;
import object.Obj_Chest;
import object.Obj_Door;
import object.Obj_Key;

public class AssetsSetter {
    GamePanel gamePanel;

    public AssetsSetter(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }
    public void setObject(){
        gamePanel.obj[0] = new Obj_Key(gamePanel);
        gamePanel.obj[0].worldX = 23 * gamePanel.tileSize;
        gamePanel.obj[0].worldY = 7 * gamePanel.tileSize;

        gamePanel.obj[1] = new Obj_Key(gamePanel);
        gamePanel.obj[1].worldX = 23 * gamePanel.tileSize;
        gamePanel.obj[1].worldY = 40 * gamePanel.tileSize;

        gamePanel.obj[2] = new Obj_Key(gamePanel);
        gamePanel.obj[2].worldX = 37 * gamePanel.tileSize;
        gamePanel.obj[2].worldY = 7 * gamePanel.tileSize;

        gamePanel.obj[3] = new Obj_Door(gamePanel);
        gamePanel.obj[3].worldX = 10 * gamePanel.tileSize;
        gamePanel.obj[3].worldY = 11 * gamePanel.tileSize;

        gamePanel.obj[4] = new Obj_Door(gamePanel);
        gamePanel.obj[4].worldX = 8 * gamePanel.tileSize;
        gamePanel.obj[4].worldY = 28 * gamePanel.tileSize;

        gamePanel.obj[5] = new Obj_Door(gamePanel);
        gamePanel.obj[5].worldX = 12 * gamePanel.tileSize;
        gamePanel.obj[5].worldY = 22 * gamePanel.tileSize;

        gamePanel.obj[6] = new Obj_Chest(gamePanel);
        gamePanel.obj[6].worldX = 10 * gamePanel.tileSize;
        gamePanel.obj[6].worldY = 7 * gamePanel.tileSize;

        gamePanel.obj[7] = new Obj_Boots(gamePanel);
        gamePanel.obj[7].worldX = 37 * gamePanel.tileSize;
        gamePanel.obj[7].worldY = 42 * gamePanel.tileSize;
    }

}
