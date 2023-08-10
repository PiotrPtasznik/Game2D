package object;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Obj_Key extends SuperObject  {
    GamePanel gamePanel;
    public Obj_Key(GamePanel gamePanel){

        this.gamePanel = gamePanel;

        name = "Key";
        try{
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/key.png")));
            utilityTool.scaledImage(image, gamePanel.tileSize, gamePanel.tileSize);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
