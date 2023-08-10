package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Obj_Door extends SuperObject  {
    GamePanel gamePanel;
    public Obj_Door(GamePanel gamePanel){

        this.gamePanel = gamePanel;

        name = "Door";
        try{
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/door.png")));
        }catch (IOException e){
            e.printStackTrace();
        }

        collision = true;
    }
}