/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication7;

import javalib.funworld.World;
import javalib.worldimages.WorldImage;
import java.util.ArrayList;
import static javaapplication7.Grid.randStatNum;
import javalib.colors.Black;
import javalib.worldimages.OverlayImages;
import javalib.worldimages.Posn;
import javalib.worldimages.RectangleImage;

/**
 *
 * @author Isabella
 */
public class Diner extends World {

    public World dinerWorld;
    public Soul player;
    public ArrayList<Soul> souls;

    final static int WIDTH = 15;
    final static int HEIGHT = 15;

    final Soul soul1 = new Soul(7, 3, new Order(1, 2, 3, 4));
    final Soul soul2 = new Soul(10, 5, new Order(4, 3, 1, 4));
    final Soul soul3 = new Soul(13, 11, new Order(6, 2, 0, 1));
    final Soul soul4 = new Soul(2, 4, new Order(1, 5, 2, 3));

    public Diner(Soul user) {
        ArrayList<Soul> arr = new ArrayList();
        arr.add(soul1);
        arr.add(soul2);
        arr.add(soul3);
        arr.add(soul4);
        
        this.player = user;
        this.souls = arr;
    }

    public World moveLeft() {
        for (int i = 0; i < souls.size(); i++) {
            // if the player is running into a soul make a new Grid game 
            if (player.x == souls.get(i).x && player.y == souls.get(i).y) {
                souls.remove(i);
                dinerWorld = new Diner(this.player);
                ArrayList<Ingredient> arr = new ArrayList();
                Ingredient bun = new Ingredient(9, 21, "white", true);
                arr.add(bun);
                Grid game = new Grid(arr,souls.get(i).order, new Order(0,0,0,0), dinerWorld);   
                return game; 
            }
        }
        if (player.x <= 1) {
            return this;
        } else {
            return new Diner(new Soul(this.player.x - 1, this.player.y));
        }
    }

    public World moveRight() {
        for (int i = 0; i < souls.size(); i++) {
            // if the player is running into a soul make a new Grid game 
            if (player.x == souls.get(i).x && player.y == souls.get(i).y) {
                souls.remove(i);
                dinerWorld = new Diner(this.player);
                ArrayList<Ingredient> arr = new ArrayList();
                Ingredient bun = new Ingredient(9, 21, "white", true);
                arr.add(bun);
                Grid game = new Grid(arr,souls.get(i).order, new Order(0,0,0,0), dinerWorld);   
                return game; 
            }
        }
        if (player.x >= WIDTH) {
            return this;
        } else {
            return new Diner(new Soul(this.player.x + 1, this.player.y));
        }
    }

    public World moveUp() {
        for (int i = 0; i < souls.size(); i++) {
            // if the player is running into a soul make a new Grid game 
            if (player.x == souls.get(i).x && player.y == souls.get(i).y) {
                souls.remove(i);
                dinerWorld = new Diner(this.player);
                ArrayList<Ingredient> arr = new ArrayList();
                Ingredient bun = new Ingredient(9, 21, "white", true);
                arr.add(bun);
                Grid game = new Grid(arr,souls.get(i).order, new Order(0,0,0,0), dinerWorld);   
                return game; 
            }
        }
        if (player.y <= 0) {
            return this;
        } else {
            return new Diner(new Soul(this.player.x, this.player.y - 1));
        }
    }

    public World moveDown() {
        for (int i = 0; i < souls.size(); i++) {
            // if the player is running into a soul make a new Grid game 
            if (player.x == souls.get(i).x && player.y == souls.get(i).y) {
                souls.remove(i);
                dinerWorld = new Diner(this.player);
                ArrayList<Ingredient> arr = new ArrayList();
                Ingredient bun = new Ingredient(9, 21, "white", true);
                arr.add(bun);
                Grid game = new Grid(arr,souls.get(i).order, new Order(0,0,0,0), dinerWorld);   
                return game; 
            }
        }
        if (player.y >= HEIGHT) {
            return this;
        } else {
            return new Diner(new Soul(this.player.x, this.player.y + 1));
        }
    }

    public World onKeyEvent(String ke) {
        switch (ke) {
            case "left":
                return this.moveLeft();
            case "right":
                return this.moveRight();
            case "up":
                return this.moveUp();
            case "down":
                return this.moveDown();
            default:
                return this;
        }
    }
    
    
//doesnt work 
    public World onMouseClicked(Posn mouse) {
        // Ugh why cant I do this in a for loop? 
        World tempG = this;
        for (int i = 0; i < souls.size(); i++) {
            if (mouse == new Posn(souls.get(i).x, souls.get(i).y)) {
                souls.remove(i);
                ArrayList<Ingredient> arr = new ArrayList();
                Ingredient bun = new Ingredient(9, 21, "white", true);
                arr.add(bun);
                tempG = new Grid(arr, souls.get(i).order, new Order(0, 0, 0, 0), this);
            } else {
            }
        }
        return tempG;

    }

//        for (int i = 0; i < souls.size(); i++) {
//            // if the player is running into a soul make a new Grid game 
//            if (player.x == souls.get(i).x && player.y == souls.get(i).y) {
//                souls.remove(i);
//                dinerWorld = new Diner(this.player, this.souls);
//                ArrayList<Ingredient> arr = new ArrayList();
//                Ingredient bun = new Ingredient(9, 21, "white", true);
//                arr.add(bun);
//                Grid game = new Grid(arr,souls.get(i).order, new Order(0,0,0,0), dinerWorld);   
//                return game; 
//            }
//        }
    
    public WorldImage back = new RectangleImage(new Posn(0, 0), 620, 620, new Black());
    
    public WorldImage makeImage() {
        for (int i = 0; i < souls.size(); i++){
            WorldImage temp = souls.get(i).drawSoul();
            back = new OverlayImages(back, temp);
        }
        WorldImage s = player.drawPlayer();
        back = new OverlayImages(back, s);
        return back;
    }
    
    public static Diner makeRandDiner(){
        int randoX = randStatNum(0, WIDTH);
        int randoY = randStatNum(0, HEIGHT);
        Soul play = new Soul(randoX,randoY); 
        return new Diner(play);
    }
    
    public static void TestDiner(){
        
    }

}
