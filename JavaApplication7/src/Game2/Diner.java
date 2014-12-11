/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game2;

import javalib.funworld.World;
import javalib.worldimages.WorldImage;
import java.util.ArrayList;
import static Game2.Grid.randStatNum;
import javalib.colors.Black;
import javalib.colors.Red;
import javalib.worldimages.OverlayImages;
import javalib.worldimages.Posn;
import javalib.worldimages.RectangleImage;
import javalib.worldimages.TextImage;

/**
 *
 * @author Isabella
 */
public class Diner extends World {

    public Soul player;
    public ArrayList<Soul> souls;

    final static int WIDTH = 15;
    final static int HEIGHT = 15;

    public Diner(Soul user, ArrayList<Soul> souls) {
        this.player = user;
        this.souls = souls;

    }
    public WorldImage makeImage() {
        for (int i = 0; i < souls.size(); i++) {
            WorldImage temp = souls.get(i).drawSoul();
            back = new OverlayImages(back, temp);
        }
        WorldImage s = player.drawPlayer();
        back = new OverlayImages(back, s);
        return back;
    }


    public Diner moveLeft() {
        if (player.x <= 0) {
            return this;
        } else {
            return new Diner(new Soul(this.player.x - 1, this.player.y), this.souls);
        }
    }

    public Diner moveRight() {
        if (player.x >= WIDTH) {
            return this;
        } else {
            return new Diner(new Soul(this.player.x + 1, this.player.y), this.souls);
        }
    }

    public Diner moveUp() {
        if (player.y <= 0) {
            return this;
        } else {
            return new Diner(new Soul(this.player.x, this.player.y - 1), this.souls);
        }
    }

    public Diner moveDown() {
        if (player.y >= HEIGHT) {
            return this;
        } else {
            return new Diner(new Soul(this.player.x, this.player.y + 1), this.souls);
        }
    }

    public Diner onKeyEvent(String ke) {
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

    public int countSaved() {
        int count = 0;
        for (int i = 0; i < souls.size(); i++) {
            Soul current = souls.get(i);
            if (current.saved) {
                count = count + 1;
            }
        }
        return count;
    }

    public boolean WinHuh() {
//        return souls.isEmpty();
        return (countSaved() == 4);
    }

    public WorldImage winner = new TextImage(new Posn(100, 120), "You Freed Us", 32, new Red());

    public World onTick() {
//        if(this.gridBurger)
        if (WinHuh()) {
            back = new OverlayImages(back, winner);
            return this;
        }
        World tempG = this;
        for (int i = 0; i < souls.size(); i++) {
            Soul current = souls.get(i);
            if (current.saved) {
            } else
            if (player.x == current.x && player.y == current.y) {
                ArrayList<Ingredient> arr = new ArrayList();
                Ingredient bun = new Ingredient(9, 21, "white", true);
                arr.add(bun);
                Order place = souls.get(i).order;
                tempG = new Grid(arr, place, new Order(0, 0, 0, 0), this, current);
            } else {
            }
        }
        return tempG;
    }

    public WorldImage back = new RectangleImage(new Posn(0, 0), 620, 620, new Black());

    public static Diner makeRandDiner() {
        int randoX = randStatNum(0, WIDTH);
        int randoY = randStatNum(0, HEIGHT);
        Soul play = new Soul(randoX, randoY);
        // CHANGE NULL!!
        return new Diner(play, null);
    }

    public static void testDiner() {
        //tests the character moves in the directions: 
        for (int i = 0; i < 100; i++) {
            Diner diner88 = makeRandDiner();
            Diner movedDiner = diner88.onKeyEvent("right");
            if (!(diner88.player.x <= movedDiner.player.x)) {
                System.out.println("fix on tick Right");
            }
        }

        for (int i = 0; i < 100; i++) {
            Diner diner88 = makeRandDiner();
            Diner movedDiner = diner88.onKeyEvent("left");
            if (!(diner88.player.x >= movedDiner.player.x)) {
                System.out.println("fix on tick Left");
            }
        }

        for (int i = 0; i < 100; i++) {
            Diner diner88 = makeRandDiner();
            Diner movedDiner = diner88.onKeyEvent("up");
            if (!(diner88.player.y >= movedDiner.player.y)) {
                System.out.println("fix on tick Up");
            }
        }

        for (int i = 0; i < 100; i++) {
            Diner diner88 = makeRandDiner();
            Diner movedDiner = diner88.onKeyEvent("down");
            if (!(diner88.player.x <= movedDiner.player.x)) {
                System.out.println("fix on tick down");
            }
        }
    }

}
