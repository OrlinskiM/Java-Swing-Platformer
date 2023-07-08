/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Components;

import Entities.Background;
import Entities.CollisionManager;
import Entities.Floor;
import Entities.FloorManager;
import Entities.Player;
import Entities.PlayerHud;
import Handlers.KeyHandler;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 *
 * @author mateu
 */
public class GamePanel  extends JPanel implements 
        java.awt.event.ActionListener{
    final int originalTileSize = 16;
    final int scale = 4;
    public final int tileSize = originalTileSize * scale; //64
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; //1024
    public final int screenHeight = tileSize * maxScreenRow; //768
    
    KeyHandler keyH = new KeyHandler();
    javax.swing.Timer componentTimer;
    Player player = new Player(keyH, this, tileSize/2, tileSize);
    FloorManager floorManager = new FloorManager(this, player);
    CollisionManager collisionManager = new CollisionManager(this, player, floorManager);
    PlayerHud playerHud = new PlayerHud(100, 100, this, floorManager, player);
    Background background = new Background(this, player);
    
    public final boolean debug = false;
    public boolean firstStart = true;
    public boolean gameRunning = false;
    public int topScore = 0;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.white);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        
        componentTimer = new javax.swing.Timer(16,this); // 1000/60 fps = 16.667, więc ~16 ms 
        componentTimer.start();
    }

    
    public void update(){
        player.update();
        background.update();
        collisionManager.checkCollisions();
        floorManager.update();
        playerHud.update();
        
        
    }
    
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2=(Graphics2D)g;
        background.draw(g2);
        floorManager.draw(g2);
        player.draw(g2);
        playerHud.draw(g2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(keyH.jumpPressed && !gameRunning){
            startGame();
        }
        
        if(player.getY() > screenHeight*2){
            stopGame(); 
        }
        
        if(gameRunning){
            update();
            repaint(); 
        }

    }

    public void startGame(){
        if(firstStart){
            gameRunning = true;
            firstStart = false;
        }else{
            player = new Player(keyH, this, tileSize/2, tileSize);
            floorManager = new FloorManager(this, player);
            collisionManager = new CollisionManager(this, player, floorManager);
            playerHud = new PlayerHud(100, 100, this, floorManager, player);
            background = new Background(this, player);
            gameRunning = true;
        }
        
    }
    
    public void stopGame(){
        gameRunning = false;      
    }
}

