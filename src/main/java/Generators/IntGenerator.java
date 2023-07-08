/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Generators;

/**
 *
 * @author mateu
 */
public class IntGenerator {

    public IntGenerator() {
    }
    
    public int generateIntInRange(int min, int max){
        return (int)Math.floor(Math.random()*(max-min+1)+min);
    }
}
