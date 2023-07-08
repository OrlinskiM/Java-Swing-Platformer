/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import Components.GamePanel;
import Handlers.KeyHandler;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mateu
 */
public class Floor {
    int x = 0;
    int y = 0;
    int sizeY = 0;
    int sizeX = 0;
    int index = 0;
    BufferedImage bufferedImage;
    
    public Floor(KeyHandler keyH, GamePanel gp) {
    }
    
    public Floor(int x, int y, int sizeX, int sizeY, int index) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.x = x;
        this.y = y;
        this.index = index;
    }
    
    public Floor(int x, int y, int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.x = x;
        this.y = y;

    }
    
    public void update(){
        
    }
        
    
    public void draw(Graphics2D g2){

        java.awt.GradientPaint gradient = new java.awt.GradientPaint(x,y,new
        java.awt.Color(0xabc8e3),x+500,y+500,new java.awt.Color(0x556677),false);
        g2.setPaint(gradient);
        g2.fillRect(x, y, sizeX, sizeY);
        
//        g2.setColor(Color.blue);
//        g2.drawString(String.valueOf(index) + " " + String.valueOf(x) + " " + String.valueOf(y) + " " + String.valueOf(sizeX) + " " + String.valueOf(sizeY), x, y);
    }
}
