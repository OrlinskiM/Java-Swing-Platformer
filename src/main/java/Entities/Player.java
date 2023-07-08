/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import Animations.AnimationRaster;
import Components.GamePanel;
import Handlers.KeyHandler;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import mati.orlinskimateusz_projekt.OrlinskiMateusz_Projekt;

/**
 *
 * @author mateu
 */
public class Player{
    int x;
    int y;
    int sizeX;
    int sizeY;
    
    private BufferedImage bufferedImage;
    private int keyFrame = 0;
    private int startFrame = 0;
    private int maxFrame = 2;
    private int delayActual = 1;
    private int delay = 8;
    private double animationScale = 1.6;
    private int animationOffsetX = 0;
    
    int speed = 3;
    int jumpForce = 20;
    int velocity = 0;
    int horizontalVelocity = 0;
    int maxHorizontalVelocity = 26;
    int gravity = 2;
    boolean onTheGround = false;
    
    KeyHandler keyH;
    GamePanel gp;

    public Player(KeyHandler keyH, GamePanel gp, int sizeX, int sizeY) {
        this.keyH = keyH;
        this.gp = gp;
        this.x = gp.screenWidth/2;
        this.y = gp.screenHeight - gp.tileSize*2;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        try {
            this.bufferedImage=javax.imageio.ImageIO.read(getClass().getResource("/img/player.png"));
        } catch (IOException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void update(){
        if(keyH.leftPressed == true){
            if(horizontalVelocity >= -maxHorizontalVelocity){
                horizontalVelocity -= speed; 
            }
        }else if(keyH.rightPressed == true){
            if(horizontalVelocity <= maxHorizontalVelocity){
                horizontalVelocity += speed; 
            }
        }else applyFriction();
        x += horizontalVelocity;
        
        if(keyH.jumpPressed == true && onTheGround){
            velocity = 0;
            if(y>gp.screenHeight/2){
                velocity += jumpForce + Math.abs(horizontalVelocity/2) + 6;
            }else{
                velocity += jumpForce + Math.abs(horizontalVelocity/3);
            }
            onTheGround = false;
        }
        if(!onTheGround){
            y -= velocity;
            velocity -= gravity;
        }
        
        updateAnimation();
    }
    
    public void draw(Graphics2D g2){

        AffineTransform affineTransform = new AffineTransform();
        affineTransform.translate(x - gp.tileSize/4 + animationOffsetX, y - gp.tileSize/2);
        BufferedImage actualFrame=bufferedImage.getSubimage(keyFrame*37 +2, 0, 37, gp.tileSize);
        affineTransform.scale(animationScale, Math.abs(animationScale));
//        Graphics actualFrameGraph = actualFrame.getGraphics();
//        actualFrameGraph.setColor(Color.red);
//    actualFrameGraph.drawString(Integer.toString(keyFrame), 5, 10);
        g2.drawImage(actualFrame, affineTransform, null);
        
        if(gp.debug){
            g2.setColor(Color.white);
            g2.fillRect(x, y, sizeX, sizeY);
            g2.drawString("vel: " + String.valueOf(velocity) + "\n horvel:" + String.valueOf(horizontalVelocity) + 
                    " " + String.valueOf(y) + " " + String.valueOf(sizeX) + " " + String.valueOf(sizeY), 200, 200);
        }
        
    }
    
    public void applyFriction(){
        if(horizontalVelocity < 0){
            horizontalVelocity += speed;
        }
        if(horizontalVelocity > 0){
            horizontalVelocity -= speed;
        }
    }
    
    public void updateAnimation(){
        if(horizontalVelocity == 0){
            if(startFrame!=0){
                delayActual = 0;
                keyFrame = 0;
            }
            startFrame = 0;
            maxFrame = 2;
        }else if(horizontalVelocity>0){
             if(startFrame!=3){
                delayActual = 0;
                keyFrame = 3;

            }
             if(animationScale<0){
                animationScale = -animationScale;
                animationOffsetX = 0;
             }
            startFrame = 3;
            maxFrame = 5;
        }else if(horizontalVelocity<0){
             if(startFrame!=3){
                delayActual = 0;
                keyFrame = 3;
            }
            if(animationScale>0){
               animationScale = -animationScale;
               animationOffsetX = 60;
            }
            startFrame = 3;
            maxFrame = 5;
        }
        
        
        if (delayActual>0){ 
            delayActual--; 
        }
        else{ 
            delayActual=delay;
            if (keyFrame<maxFrame){ 
                    keyFrame++;
                }
            else{ 
                keyFrame=startFrame; 
            }
        }
    }

    public int getX() {
        return x;
    }


    public int getY() {
        return y;
    }

    
    
}
