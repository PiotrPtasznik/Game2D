package main;


import entities.Player;
import object.SuperObject;
import tile.TileManager;

import javax.swing.JPanel;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    //screen settings
    final int originalTileSize = 16; // 16x16 tile
    final int scale = 3; //scaling

    public final int tileSize = originalTileSize*scale; // 48 x 48 tile
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize*maxScreenCol; //768px width (48px x 16)
    public final int screenHeight = tileSize* maxScreenRow; //576px height (48px x 12 )

    public final int maxWorldCol =50;
    public final int maxWorldRow = 50;

    int FPS = 60; //FPS

    //SYSTEM
    TileManager tileManager = new TileManager(this);
    MovementHandler movementHandler = new MovementHandler();
    Sound sound = new Sound();

    Sound music = new Sound();
    public CollisionChecker collisionChecker = new CollisionChecker(this);
    public AssetsSetter assetsSetter = new AssetsSetter(this);
    public UI ui = new UI(this);
    Thread gameThread;

    //ENTITY & OBJECTS
    public Player player = new Player(this, movementHandler);
    public SuperObject obj [] = new SuperObject[10];


    public GamePanel () {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(movementHandler);
        this.setFocusable(true);
    }

    public void setupGame(){
        assetsSetter.setObject();
        playMusic(0);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = (double) 1000000000/FPS; // 1000000000nanosecs = 1 sec, so 1sec/60 = 0,01666 seconds. Frame is drawn every 0,01666(6) second
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null){
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime-lastTime);
            lastTime = currentTime;

            if(delta >= 1){
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if(timer >= 1000000000){
                System.out.println("FPS:" + drawCount);
                drawCount = 0 ;
                timer = 0;
            }
        }
    }

    public void update(){

        player.update();
    }

    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);

        Graphics2D graphics2D = (Graphics2D)graphics;

        tileManager.draw(graphics2D);

        for (int i = 0; i <obj.length; i++){
            if(obj[i] != null){
                obj[i].draw(graphics2D, this);
            }
        }

        player.draw(graphics2D);
        ui.draw(graphics2D);

        graphics2D.dispose();
    }
    public void playMusic(int i){
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic(){

        sound.stop();
    }

    public void playSE(int i){
        sound.setFile(i);
        sound.play();
    }
}
