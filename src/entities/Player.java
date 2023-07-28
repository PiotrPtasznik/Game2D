package entities;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;


public class Player extends Entity {
    GamePanel gamePanel;
    KeyHandler keyHandler;
    public final int screenX;
    public final int screenY;
    int hasKey = 0;

    public Player(GamePanel gamePanel, KeyHandler keyHandler){

        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;

        screenX = gamePanel.screenWidth/2 - (gamePanel.tileSize/2);
        screenY = gamePanel.screenHeight/2 - (gamePanel.tileSize/2);

        solidArea = new Rectangle(8,16,32,32);

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
        try{
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_up_1.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_up_2.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_down_1.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_down_2.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_left_1.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_left_2.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_right_1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_right_2.png")));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void update(){
        if(keyHandler.upPressed || keyHandler.downPressed || keyHandler.leftPressed || keyHandler.rightPressed){

            if (keyHandler.upPressed){
                direction = "up";
            }
            if (keyHandler.downPressed){
                direction = "down";
            }
            if (keyHandler.leftPressed){
                direction = "left";
            }
            if (keyHandler.rightPressed){
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
                    hasKey++;
                    gamePanel.obj[i] = null;
                    System.out.println("Key : " + hasKey);
                    break;
                case "Door":
                    if (hasKey>0){
                        gamePanel.obj[i] = null;
                        hasKey--;
                    }
                    System.out.println("Key : " + hasKey);
                    break;
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

        g2D.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null );
    }
}
