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

    public Diner moveLeft() {
        if (player.x <= 0) {
            return this;
        } else {
            return new Diner(new Soul(this.player.x - 1, this.player.y));
        }
    }

    public Diner moveRight() {
        if (player.x >= WIDTH) {
            return this;
        } else {
            return new Diner(new Soul(this.player.x + 1, this.player.y));
        }
    }

    public Diner moveUp() {
        if (player.y <= 0) {
            return this;
        } else {
            return new Diner(new Soul(this.player.x, this.player.y - 1));
        }
    }

    public Diner moveDown() {
        if (player.y >= HEIGHT) {
            return this;
        } else {
            return new Diner(new Soul(this.player.x, this.player.y + 1));
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

    public boolean WinHuh() {
        return souls.isEmpty();
    }

    public WorldImage winner = new TextImage(new Posn(100, 100), "You Freed Us", 32, new Red());

    public World onTick() {
        if (WinHuh()) {
            back = new OverlayImages(back, winner);
            return this;
        }
        World tempG = this;
        for (int i = 0; i < souls.size(); i++) {
            if (player.x == souls.get(i).x && player.y == souls.get(i).y) {
                ArrayList<Ingredient> arr = new ArrayList();
                Ingredient bun = new Ingredient(9, 21, "red", true);
                arr.add(bun);
                Order place = souls.get(i).order;
                souls.remove(i);
                tempG = new Grid(arr, place, new Order(0, 0, 0, 0), this);
            } else {
            }
        }
        return tempG;
    }

    public WorldImage back = new RectangleImage(new Posn(0, 0), 620, 620, new Black());

    public WorldImage makeImage() {
        for (int i = 0; i < souls.size(); i++) {
            WorldImage temp = souls.get(i).drawSoul();
            back = new OverlayImages(back, temp);
        }
        WorldImage s = player.drawPlayer();
        back = new OverlayImages(back, s);
        return back;
    }

    public static Diner makeRandDiner() {
        int randoX = randStatNum(0, WIDTH);
        int randoY = randStatNum(0, HEIGHT);
        Soul play = new Soul(randoX, randoY);
        return new Diner(play);
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
