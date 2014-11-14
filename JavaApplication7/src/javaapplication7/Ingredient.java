/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javaapplication7;

import javalib.colors.*;
/**
 *
 * @author Isabella
 */
public class Ingredient {
    
    public int x, y;
    
    public Ingredient(int ex, int why){
        this.x = ex;
        this.y = why;
    } 
    
    public int getX(){
        return this.x;
    }
    
    public int getY(){
        return this.y;
    }
}
