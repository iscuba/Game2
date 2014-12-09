/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication7;

import javalib.funworld.World;
import javalib.worldimages.WorldImage;
import java.util.ArrayList;
import javalib.worldimages.Posn;

/**
 *
 * @author Isabella
 */
public class Diner extends World {

    public World dinerWorld;
    public Soul player;
    public ArrayList<Soul> souls;

    final int WIDTH = 10;
    final int HEIGHT = 10;

    final Soul soul1 = new Soul(2, 2, new Order(1, 2, 3, 4));
    final Soul soul2 = new Soul(4, 5, new Order(4, 3, 1, 4));
    final Soul soul3 = new Soul(6, 8, new Order(6, 2, 0, 1));
    final Soul soul4 = new Soul(2, 4, new Order(1, 5, 2, 3));

    public Diner(Soul user, ArrayList<Soul> souls) {
        this.player = user;
        this.souls = souls;
    }

    public World moveLeft() {
        if (player.x <= WIDTH) {
            return this;
        } else {
            return new Diner(new Soul(this.player.x - 1, this.player.y), this.souls);
        }
    }

    public World moveRight() {
        if (player.x >= WIDTH) {
            return this;
        } else {
            return new Diner(new Soul(this.player.x + 1, this.player.y), this.souls);
        }
    }

    public World moveUp() {
        if (player.y <= HEIGHT) {
            return this;
        } else {
            return new Diner(new Soul(this.player.x, this.player.y - 1), this.souls);
        }
    }

    public World moveDown() {
        if (player.y <= HEIGHT) {
            return this;
        } else {
            return new Diner(new Soul(this.player.x, this.player.y + 1), this.souls);
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

    public World onMouseClicked(Posn mouse) {
        if (mouse == new Posn(soul1.x, soul1.y)) {
            souls.remove(soul1);
            dinerWorld  = new Diner(this.player, this.souls);
            ArrayList<Ingredient> arr = new ArrayList();
            Ingredient bun = new Ingredient(9, 21, "white", true);

            arr.add (bun);
            Grid game = new Grid(arr, souls.get(i).order, new Order(0, 0, 0, 0), dinerWorld);
            return game ;

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
        }

     {
            
    

    public WorldImage makeImage() {

    }

}
