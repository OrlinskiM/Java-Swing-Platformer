/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package mati.orlinskimateusz_projekt;

import Components.GamePanel;
import javax.swing.JFrame;

/**
 *
 * @author mateu
 */
public class OrlinskiMateusz_Projekt {

    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Mateusz Orliński");
        
         GamePanel gamePanel = new GamePanel();
        
        window.add(gamePanel);
        window.pack();
        
        window.setVisible(true);
    }
}
