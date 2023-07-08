/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import Components.GamePanel;
import Generators.IntGenerator;
import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 *
 * @author mateu
 */
public class FloorManager {
    public java.util.List<Floor> floorList = new ArrayList();
    public java.util.List<Floor> wallList = new ArrayList();
    private final GamePanel gp; 
    private final IntGenerator intGenerator = new IntGenerator();

    Player player;
    int scrollSpeed = 3;
    int maxFloorCol;
    int minFloorSize = 3;
    int maxFloorsRendered = 7;
    int floorDifficulty;
    public int currentScore = 0;
    int difficultyFloor = 50; //na ktorym poziomie zwiekszy sie trudnosc

    public FloorManager(GamePanel gp, Player player) {
        this.gp = gp;
        this.player = player;
        this.floorDifficulty = gp.maxScreenCol/2;
        this.maxFloorCol = gp.maxScreenCol-2;
        
        wallList.add(new Floor(0,0,gp.tileSize,gp.screenHeight));
        wallList.add(new Floor(gp.screenWidth-gp.tileSize,0,gp.tileSize,gp.screenHeight ));
        
        
        floorList.add(new Floor(0, gp.screenHeight-gp.tileSize, gp.screenWidth, gp.tileSize, 0));
//        floorList.add(new Floor(0, 300, 300, gp.tileSize));
//        floorList.add(new Floor(400, 400, 300, gp.tileSize));
//        floorList.add(new Floor(400, 100, 300, gp.tileSize));
//        floorList.add(new Floor(100, -100, 300, gp.tileSize));
        generateFloors();
        
    }

    public void update() {
        java.util.Iterator<Floor> floorIterator = floorList.iterator(); // ruch platform w dół
        player.y += scrollSpeed;
            while(floorIterator.hasNext()){
                Floor floor = floorIterator.next();
                floor.y += scrollSpeed;
                if(player.y < gp.screenHeight/2 &&
                player.velocity>=0){
                    floor.y += player.velocity;
                }
                if(floor.y > gp.screenHeight+gp.tileSize){
                    currentScore = floor.index;
                    floorIterator.remove();
                }
            }
            generateFloors();
            increaseDifficulty();
        
    }

    public void draw(Graphics2D g2) {
        java.util.Iterator<Floor> wallIterator = wallList.iterator();
        java.util.Iterator<Floor> floorIterator = floorList.iterator();
        while(floorIterator.hasNext()){
            floorIterator.next().draw(g2);
        }
        while(wallIterator.hasNext()){
            wallIterator.next().draw(g2);
        }
    }
    
    public void generateFloors(){
        while(floorList.size()<maxFloorsRendered){
            int generateSize = intGenerator.generateIntInRange(minFloorSize, floorDifficulty);
            int generatePosition = intGenerator.generateIntInRange(1, maxFloorCol - generateSize+1);
            
            int x = generatePosition*gp.tileSize;
            int y = floorList.get(floorList.size()-1).y - gp.tileSize*2;
            int sizeX = generateSize*gp.tileSize;
            int sizeY = gp.tileSize;
            
            floorList.add(new Floor(x, y, sizeX, sizeY, floorList.get(floorList.size()-1).index+1));
        }
    }
    
    public void increaseDifficulty(){
        if(currentScore%difficultyFloor == 0){
            scrollSpeed = currentScore/difficultyFloor + 3;
        }
    }
}
