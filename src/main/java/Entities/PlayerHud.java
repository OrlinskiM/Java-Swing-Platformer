/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import Components.GamePanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

/**
 *
 * @author mateu
 */
public class PlayerHud {
    int x;
    int y;
    GamePanel gp;
    FloorManager floorManager;
    Player player;

    public PlayerHud(int x, int y, GamePanel gp, FloorManager floorManager, Player player) {
        this.x = x;
        this.y = y;
        this.gp = gp;
        this.floorManager = floorManager;
        this.player = player;
    }
    
    public void update(){
    }
    
    public void draw(Graphics2D g2){
        g2.setColor(Color.white);
        g2.setFont(new Font(Font.SANS_SERIF,Font.BOLD,80));
        if(!gp.gameRunning || player.y>gp.screenHeight*1.5){
            checkTopScore();
            g2.drawString("Top Score: " + String.valueOf(gp.topScore), gp.screenWidth/4, gp.screenHeight/2);   
        }else{
            g2.drawString(String.valueOf(floorManager.currentScore), x, y);   
        }
 
    }
    
    public void checkTopScore(){
           if(gp.topScore<floorManager.currentScore){
                gp.topScore = floorManager.currentScore;
           }
    }
}
