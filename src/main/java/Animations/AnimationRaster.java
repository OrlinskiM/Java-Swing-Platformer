/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Animations;

/**
 *
 * @author mateu
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mateu
 */
public class AnimationRaster {

    private int x;
    private int y;
    private int width;
    private int height;
    private int delayActual = 1;
    private int delay;
    private int orientation;
    private float alpha;
    private BufferedImage bufferedImage;
    private int keyFrame;
    private int maxFrame;
    
    public AnimationRaster(int x,int y, int width, int height, int delay, int orientation, float alpha, int maxFrame, String imgDir){
            this.x=x;
            this.y=y;
            this.width=width;
            this.height=height;
            this.delay=delay;
            this.orientation=orientation;
            this.alpha=alpha;
            this.maxFrame=maxFrame;
            
        try {
            this.bufferedImage=javax.imageio.ImageIO.read(AnimationRaster.class.getResource(imgDir));
        } catch (IOException ex) {
            Logger.getLogger(AnimationRaster.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    
    public void action(){
        if (delayActual>0){ 
            delayActual--; 
        }
        else{ 
            delayActual=delay;
            orientation=orientation+2;
            if (keyFrame<maxFrame){ 
                    keyFrame++;
                }
            else{ 
                keyFrame=0; 
            }
        }
    }
    
    public void paintObject(Graphics2D g2){ 
        g2.setColor(Color.BLACK);
        AffineTransform affineTransform = new AffineTransform();
        //affineTransform.rotate(Math.toRadians(orientation),x,y); //nie mam pojecia czemu równoczesny obrot i translacja nie działają
        affineTransform.translate(x, y);
        BufferedImage actualFrame=bufferedImage.getSubimage(keyFrame*width, 0, width, height);
//        affineTransform.scale(0.5, 0.5);
//        Graphics actualFrameGraph = actualFrame.getGraphics();
//        actualFrameGraph.setColor(Color.red);
//    actualFrameGraph.drawString(Integer.toString(keyFrame), 5, 10);
        g2.drawImage(actualFrame, affineTransform, null);
    }
}

