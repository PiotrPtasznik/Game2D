package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Obj_Chest extends SuperObject{
    GamePanel gamePanel;
        public Obj_Chest(GamePanel gamePanel){

            this.gamePanel = gamePanel;

            name = "Chest";
            try{
                image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/chest.png")));
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

