/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import Components.GamePanel;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.TexturePaint;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mateu
 */
public class Background {
    BufferedImage bufferedImage;
    GamePanel gp;
    Player player;
    
    int imgSize = 500;
    int x = 0;
    int y = 0;

    public Background(GamePanel gp, Player player) {
        this.gp = gp;
        this.player = player;
        try {
            this.bufferedImage = javax.imageio.ImageIO.read(getClass().getResource("/img/bg.jpg"));
        } catch (IOException ex) {
            Logger.getLogger(Background.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
    public void update(){
        y -= 2;
        if(player.velocity>0 && player.y < gp.screenHeight/2){
            y -= player.velocity;
        }
        if(y < 0){
            y = imgSize;
        }
        
    }
        
    
    public void draw(Graphics2D g2){
        BufferedImage actualFrame=bufferedImage.getSubimage(0, y, 500 , 500);
        TexturePaint  texturePattern = new java.awt.TexturePaint(actualFrame,new java.awt.Rectangle(0,0,200,200));
         g2.setPaint(texturePattern);
         g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
//        g2.fillRect(x, y, sizeX, sizeY);
//        g2.setColor(Color.blue);
//        g2.drawString(String.valueOf(index) + " " + String.valueOf(x) + " " + String.valueOf(y) + " " + String.valueOf(sizeX) + " " + String.valueOf(sizeY), x, y);
    }
}
