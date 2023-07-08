/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import Components.GamePanel;

/**
 *
 * @author mateu
 */
public class CollisionManager {
    GamePanel gp;
    Player player;
    FloorManager floorManager;


    public CollisionManager(GamePanel gp, Player player, FloorManager floorManager) {
        this.gp = gp;
        this.player = player;
        this.floorManager = floorManager;
    }
    
    public void checkCollisions(){
        collideVertical();
        collideHorizontal();
    }
    
    public void collideVertical(){
            java.util.Iterator<Floor> floorIterator = floorManager.floorList.iterator();
        while(floorIterator.hasNext()){
            Floor floor = floorIterator.next();
            if(player.velocity<=0){
                int collisionScale = -player.velocity;
                
                if(player.y + player.sizeY >= floor.y &&
                   player.y + player.sizeY <= floor.y + collisionScale &&
                   player.x + player.sizeX >= floor.x &&
                   player.x <= floor.x + floor.sizeX){

                    player.y = floor.y - player.sizeY;
                    player.onTheGround = true;
                    player.velocity = 0;
                    break;
                }else{
                    player.onTheGround = false;
                }
            }
        }
        
    }
    
    public void collideHorizontal(){
        Floor wall = floorManager.wallList.get(0);
        if(player.x <= wall.x + wall.sizeX){
            player.x = wall.x + wall.sizeX;
            player.horizontalVelocity = -player.horizontalVelocity;
        }
        
        wall = floorManager.wallList.get(1);
        if(player.x + player.sizeX >= wall.x){
            player.x = wall.x - player.sizeX;
            player.horizontalVelocity = -player.horizontalVelocity;
        }
    }
    
}
