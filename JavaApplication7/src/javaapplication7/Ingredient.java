/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javaapplication7;

import java.util.Random;
import javalib.colors.*;
import javalib.worldimages.FrameImage;
import javalib.worldimages.Posn;
/**
 *
 * @author Isabella
 */
public class Ingredient {
    
    public int x, y;
    public String color;
    public boolean stacked;
    
    public Ingredient(int ex, int why, String color, boolean s){
        this.x = ex;
        this.y = why;
        this.color = color;
        this.stacked = s;
    } 
    
    public int getX(){
        return this.x;
    }
    
    public int getY(){
        return this.y;
    }
    
    public Ingredient stackIt(){
        return new Ingredient(this.x, this.y, this.color, true);
    }
    
    public boolean onTop(Ingredient ing){
        return (this.x == ing.x && this.y - 1 == ing.y);
    }
    
    public FrameImage drawIngredient() {
        return new FrameImage(new Posn(this.x * 10, this.y*10), 60, 20, new Green());
//        switch(this.color){
//            case "green":
//                return new FrameImage(new Posn(this.x * 20, this.y * 20), 60, 20, new Green());
//            case "blue":
//                return new FrameImage(new Posn(this.x * 20, this.y * 20), 60, 20, new Blue());
//            case "yellow":
//                return new FrameImage(new Posn(this.x * 20, this.y * 20), 60, 20, new Yellow());
//            case "red":
//                return new FrameImage(new Posn(this.x * 20, this.y * 20), 60, 20, new Red());
//            default :
//                return new FrameImage(new Posn(this.x * 20, this.y * 20), 60, 20, new White());
//        }
        
    }
    
    public static String randColor() {
        int rando = randNum(1, 4);
        switch (rando) {
            case 1:
                return "green";
            case 2:
                return "blue";
            case 3:
                return "yellow";
            case 4:
                return "red";
            default:
                return "white";
        }
    }
    
    public static int randNum(int min, int max){
        Random rando = new Random();
        int randomNum = rando.nextInt((max - min) + 1) + min;
        return randomNum;
    }
    
    public static Ingredient makeRandIngredient(){
        int ex = randNum(1,18);
        int wy = randNum(1,18);
        return new Ingredient(ex,wy,randColor(),false);
    }
    
    public static Ingredient makeStackIngredient(int x, int y){
        return new Ingredient(x,y, randColor(),true);
    }
}
