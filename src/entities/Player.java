package entities;

import main.GamePanel;
import main.MovementHandler;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;


public class Player extends Entity {
    GamePanel gamePanel;
    MovementHandler movementHandler;
    public final int screenX;
    public final int screenY;
    public int hasKey = 0;

    public Player(GamePanel gamePanel, MovementHandler movementHandler){

        this.gamePanel = gamePanel;
        this.movementHandler = movementHandler;

        screenX = gamePanel.screenWidth/2 - (gamePanel.tileSize/2);
        screenY = gamePanel.screenHeight/2 - (gamePanel.tileSize/2);

        solidArea = new Rectangle(8,16,30,30);

        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){
        worldX = gamePanel.tileSize*23;
        worldY = gamePanel.tileSize*21;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage(){

        up1 = setup("boy_up_1");
        up2 = setup("boy_up_2");
        down1 = setup("boy_down_1");
        down2 = setup("boy_down_2");
        left1 = setup("boy_left_1");
        left2 = setup("boy_left_2");
        right1 = setup("boy_right_1");
        right2 = setup("boy_right_2");

    }

    public BufferedImage setup(String imageName){
        UtilityTool utilityTool = new UtilityTool();
        BufferedImage scaledImage = null;
        try {
            scaledImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/" + imageName + ".png")));
            scaledImage = utilityTool.scaledImage(scaledImage, gamePanel.tileSize, gamePanel.tileSize);
        }catch (IOException e){
            e.printStackTrace();
        }
        return scaledImage;
    }

    public void update(){
        if(movementHandler.upPressed || movementHandler.downPressed || movementHandler.leftPressed || movementHandler.rightPressed){

            if (movementHandler.upPressed){
                direction = "up";
            }
            if (movementHandler.downPressed){
                direction = "down";
            }
            if (movementHandler.leftPressed){
                direction = "left";
            }
            if (movementHandler.rightPressed){
                direction = "right";

            }

            //check tile collision
            collisionOn = false;
            gamePanel.collisionChecker.checkTile(this);

            //check object collision
            int objIndex = gamePanel.collisionChecker.checkObject(this, true);
            interactionWithObject(objIndex);

            //Collision false - player can move
            if(!collisionOn){
                switch (direction) {
                    case "up" -> worldY -= speed;
                    case "down" -> worldY += speed;
                    case "left" -> worldX -= speed;
                    case "right" -> worldX += speed;
                }
            }

            spriteCounter++;

            if(spriteCounter > 10){
                if(spriteNum == 1){
                    spriteNum =2;
                }
                else if(spriteNum ==2) {
                    spriteNum=1;
                }
                spriteCounter = 0;
            }

        }

    }

    public void interactionWithObject(int i){
        if(i != 999){
            String objectName = gamePanel.obj[i].name;

            switch (objectName){
                case "Key":
                    gamePanel.playSE(1);
                    hasKey++;
                    gamePanel.obj[i] = null;
                    gamePanel.ui.showMessage("You got a key");
                    break;
                case "Door":
                    if (hasKey>0){
                        gamePanel.playSE(3);
                        gamePanel.obj[i] = null;
                        hasKey--;
                        gamePanel.ui.showMessage("You opened door");
                    }
                    else {
                        gamePanel.ui.showMessage("You need key");
                    }
                    break;
                case "Boots":
                    gamePanel.playSE(2);
                    speed += 1;
                    gamePanel.obj[i] = null;
                    break;
                case "Chest":
                    gamePanel.ui.gameFinished = true;
                    gamePanel.stopMusic();
                    gamePanel.playSE(4);

            }
        }

    }

    public void draw(Graphics2D g2D){

        BufferedImage image = null;

        switch (direction) {
            case "up" -> {
                if (spriteNum == 1) {
                    image = up1;
                } else if (spriteNum == 2) {
                    image = up2;
                }
            }
            case "down" -> {
                if (spriteNum == 1) {
                    image = down1;
                } else if (spriteNum == 2) {
                    image = down2;
                }
            }
            case "left" -> {
                if (spriteNum == 1) {
                    image = left1;
                } else if (spriteNum == 2) {
                    image = left2;
                }
            }
            case "right" -> {
                if (spriteNum == 1) {
                    image = right1;
                } else if (spriteNum == 2) {
                    image = right2;
                }
            }
        }

        g2D.drawImage(image, screenX, screenY, null );
    }
}
