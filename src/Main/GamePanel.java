package Main;

import javax.swing.JPanel;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    //screen settings
    final int originalTileSize = 16; // 16x16 tile
    final int scale = 3; //scaling

    final int tileSize = originalTileSize*scale; // 48 x 48 tile
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize*maxScreenCol; //768px width (48px x 16)
    final int screenHeight = tileSize* maxScreenRow; //576px height (48px x 12 )

    KeyHandler keyHandler = new KeyHandler();
    Thread gameThread;

    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    public GamePanel () {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        while (gameThread != null){
            long currentTime = System.nanoTime();
            System.out.println("Current time" + currentTime);
            update();
            repaint();
        }
    }

    public void update(){
        if (keyHandler.upPressed){
            playerY -= playerSpeed;
        }
        else if (keyHandler.downPressed){
            playerY += playerSpeed;
        }
        else if (keyHandler.leftPressed){
            playerX -= playerSpeed;
        }
        else if (keyHandler.rightPressed){
            playerX += playerSpeed;
        }
    }

    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        Graphics2D g2 = (Graphics2D)graphics;

        g2.setColor(Color.white);
        g2.fillRect(playerX, playerY, tileSize, tileSize);
        g2.dispose();

    }
}
