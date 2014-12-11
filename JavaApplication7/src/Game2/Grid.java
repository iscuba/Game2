/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game2;

import static Game2.Ingredient.randColor;
import java.util.ArrayList;
import java.util.Random;
import javalib.colors.Black;
import javalib.colors.Blue;
import javalib.colors.Red;
import javalib.worldimages.FrameImage;
import javalib.worldimages.OverlayImages;
import javalib.worldimages.Posn;
import javalib.worldimages.RectangleImage;
import javalib.worldimages.WorldImage;
import javalib.funworld.*;
import javalib.worldimages.TextImage;

/**
 *
 * @author Isabella
 */
public class Grid extends World {

    final int MAX = 18;
    final int MIN = 1;
    public Order ticket;
    public Diner oldWorld;
    public Order check;
    public ArrayList<Ingredient> stack;

    public Grid(ArrayList<Ingredient> stack, Order o, Order c, Diner w) {
        this.stack = stack;
        this.ticket = o;
        this.check = c;
        this.oldWorld = w;
    }

    public WorldImage back = new RectangleImage(new Posn(0, 0), 780, 860, new Black());
    public WorldImage frame = new FrameImage(new Posn(0, 0), 780, 860, new Blue());
    public WorldImage backdrop = new OverlayImages(back, frame);
//    public String mustStack = ("Please Stack: " + ticket.blue+ " blue "+ ticket.green+
//            " green "+ticket.red+" red "+ticket.yellow+" yellow");
//    public WorldImage mustStackImage = new TextImage(new Posn(780, 330), mustStack, new Red());
//    public WorldImage backScore = new OverlayImages(backdrop, mustStackImage);
//    public String score = "Stacked: " + check.blue+ " blue "+ check.green+
//            " green "+check.red+" red "+ check.yellow+" yellow";;
//    public WorldImage scoreBoard = new TextImage(new Posn(780,430),score,new Red());
//    public WorldImage backScoreBoard = new OverlayImages(backScore,scoreBoard);
//            
    public WorldImage makeImage() {
        for (int i = 0; i < stack.size(); i++) {
            WorldImage b = stack.get(i).drawIngredient();
            backdrop = new OverlayImages(backdrop, b);
        }
        //WorldImage score = new TextImage(new Posn(230, 600), "Score: " + stack.size(), 13, -1, new Red());
        //return new OverlayImages(backdrop, score);
        return backdrop;

    }

    public int randNum(int min, int max) {
        Random rando = new Random();
        int randomNum = rando.nextInt((max - min) + 1) + min;
        return randomNum;
    }

    // my on key uses this to move the stack left or right
    public Grid moveStackLeft() {
        ArrayList<Ingredient> tempStack = new ArrayList<>();
        for (int i = 0; i < stack.size(); i++) {
            Ingredient current = stack.get(i);
            if (!current.stacked) {
                tempStack.add(current);
            }
            if (current.stacked) {
                if (current.x <= MIN) {
                    tempStack.add(current);
                } else {
                    tempStack.add(new Ingredient(current.x - 1, current.y, current.color, current.stacked));
                }
            }
        }
        return new Grid(tempStack, this.ticket, this.check, this.oldWorld);
    }

    public Grid moveStackRight() {
        ArrayList<Ingredient> tempStack = new ArrayList<>();
        for (int i = 0; i < this.stack.size(); i++) {
            Ingredient current = stack.get(i);
            if (!current.stacked) {
                tempStack.add(current);
            }
            if (current.stacked) {
                if (current.x >= MAX) {
                    tempStack.add(current);
                } else {
                    tempStack.add(new Ingredient(current.x + 1, current.y, current.color, current.stacked));
                }
            }
        }
        return new Grid(tempStack, this.ticket, this.check, this.oldWorld);
    }

    public Grid dropRandIngredient() {
        //       int drophuh = randNum(1, 5);
        //       if (drophuh == 3) {
        int randoX = randNum(1, 18);
        Ingredient newIng = new Ingredient(randoX, 1, randColor(), false);
        ArrayList<Ingredient> addedStack = new ArrayList();
        for (int i = 0; i < this.stack.size(); i++) {
            addedStack.add(this.stack.get(i));
        }
        addedStack.add(newIng);
        return new Grid(addedStack, this.ticket, this.check, this.oldWorld);
        //       } else {
        //           return this;
        //      }
    }

    public Grid onKeyEvent(String ke) {
        switch (ke) {
            case "left":
                return this.moveStackLeft();
            case "right":
                return this.moveStackRight();
            case "down":
                return this.dropRandIngredient();
            default:
                return this;
        }
    }

    public int countGreen() {
        int count = 0;
        for (int i = 0; i < stack.size(); i++) {
            Ingredient current = stack.get(i);
            if (current.stacked && current.color.equals("green")) {
                count++;
            }
        }
        return count;
    }

    public int countBlue() {
        int count = 0;
        for (int i = 0; i < stack.size(); i++) {
            Ingredient current = stack.get(i);
            if (current.stacked && current.color.equals("blue")) {
                count++;
            }
        }
        return count;
    }

    public int countYellow() {
        int count = 0;
        for (int i = 0; i < stack.size(); i++) {
            Ingredient current = stack.get(i);
            if (current.stacked && current.color.equals("yellow")) {
                count++;
            }
        }
        return count;
    }

    public int countRed() {
        int count = 0;
        for (int i = 0; i < stack.size(); i++) {
            Ingredient current = stack.get(i);
            if (current.stacked && current.color.equals("red")) {
                count++;
            }
        }
        return count;
    }

    public int countStacked() {
        int count = 0;
        for (int i = 0; i < stack.size(); i++) {
            Ingredient current = stack.get(i);
            if (current.stacked) {
                count = count + 1;
            }
        }
        return count;
    }

    public boolean winHuh() {
        return check.filled(ticket);
    }

    //if they stacked more than 15 things without completeing the order 
    public boolean looseHuh() {
        return countStacked() > 5;
    }

    //changes the stack to include Ingredients which have been successfully caught by the user
    //and moves the unstacked ingredients down the screen 
    public Grid stackIngredients() {
        ArrayList<Ingredient> tempStack = new ArrayList<>();
        for (int i = 0; i < stack.size(); i++) {
            Ingredient current = stack.get(i);
            // the falling ingredient is caught by the bun
            if (!current.stacked) {
                if (stack.get(0).x == current.x && 21 - countStacked() == current.y) {
                    tempStack.add(current.stackIt());
                } else {
                    tempStack.add(new Ingredient(current.x, current.y + 1, current.color, current.stacked));
                }
            } else {
                tempStack.add(current);
            }
        }
        Order tempO = new Order(countGreen(), countBlue(), countYellow(), countRed());
        return new Grid(tempStack, this.ticket, tempO, this.oldWorld);
    }

    public World onTick() {
        if (looseHuh()) {
            return this.oldWorld;
        } else if (winHuh()) {
            return this.oldWorld;
        }
        return this.stackIngredients();
    }

    public static int randStatNum(int min, int max) {
        if (min > max) {
            throw new RuntimeException("min " + min + " must be smaller than max " + max);
        }
        Random rando = new Random();
        int randomNum = rando.nextInt((max - min) + 1) + min;
        return randomNum;
    }

    public static Grid makeRandGrid() {
        ArrayList<Ingredient> arg = new ArrayList<>();
        int randX = randStatNum(1, 18);
        Ingredient bun = new Ingredient(randX, 21, Ingredient.randColor(), true);
        arg.add(bun);
        int randHeight = randStatNum(1, 15);
        for (int i = 0; i < randHeight; i++) {
            arg.add(new Ingredient(randX, 20 - i, Ingredient.randColor(), true));
        }
        int randFall = randStatNum(1, 5);
        for (int i = 0; i < randFall; i++) {
            int randx = randStatNum(1, 18);
            int randy = randStatNum(1, 20);
            arg.add(new Ingredient(randx, randy, Ingredient.randColor(), false));
        }
        //idk what to give it for order, or for the old world, but for right now whatever. 
        return new Grid(arg, new Order(2, 2, 2, 2), new Order(2, 2, 2, 2),null );
    }

    public static Grid makeUnStackGrid() {
        ArrayList<Ingredient> arg = new ArrayList<>();
        int randX = randStatNum(1, 18);
        Ingredient bun = new Ingredient(randX, 21, Ingredient.randColor(), true);
        arg.add(bun);
        int randHeight = randStatNum(1, 15);
        for (int i = 0; i < randHeight; i++) {
            arg.add(new Ingredient(randX, 20 - i, Ingredient.randColor(), true));
        }
        int randFall = randStatNum(1, 5);
        for (int i = 0; i < randFall; i++) {
            int randx = randStatNum(1, 18);
            int randy = randStatNum(1, 20);
            arg.add(new Ingredient(randx, randy, Ingredient.randColor(), false));

        }
        //adds a block to the top of the stack that isnt "stacked" yet 
        arg.add(new Ingredient(randX, 20 - randHeight, Ingredient.randColor(), false));
        //idk what to give it for order, but for right now whatever. 
        return new Grid(arg, new Order(2, 2, 2, 2), new Order(2, 2, 2, 2), null);
    }

    public static void testGrid() {
        //crude test for stackIngredients:
        ArrayList<Ingredient> arrg = new ArrayList<>();
        arrg.add(new Ingredient(3, 3, "blue", true));
        arrg.add(new Ingredient(3, 2, "blue", false));
        Grid crude1 = new Grid(arrg, new Order(2, 2, 2, 2), new Order(2, 2, 2, 2), null);
        Grid crude = crude1.moveStackRight();
        Grid stCrude = crude.stackIngredients();
        //System.out.println("stackX for c is: "+crude.stackX+" and for stacked: "+ stCrude.stackX);
        System.out.println("stack's height is: " + crude.stack.size() + " Stacked should be +1: " + stCrude.stack.size());

        //test moving left and right:
        Grid testGrid12 = makeRandGrid();
        int height2 = testGrid12.stack.size() - 1;
        Grid movedRight12 = testGrid12.onKeyEvent("right");
        System.out.println("stack's X is: " + testGrid12.stack.get(height2).x + " and moved right: " + movedRight12.stack.get(height2).x);
        Grid movedLeft12 = testGrid12.onKeyEvent("left");
        System.out.println("stack's X is: " + testGrid12.stack.get(height2).x + " and moved left: " + movedLeft12.stack.get(height2).x);
        Grid unStack = makeUnStackGrid();
        Grid stackedGrid = unStack.stackIngredients();
        System.out.println("height before stacking : " + unStack.stack.size() + " after should be +1 : " + stackedGrid.stack.size());

        //testing for moving the ingredients down the screen every second 
        //testing to move the grid right:
        for (int i = 0; i > 100; i++) {
            Grid testGrid1 = makeRandGrid();
            int height = testGrid1.stack.size() - 1;
            Grid movedRight1 = testGrid1.onKeyEvent("right");
            if (!(testGrid1.stack.get(height).x <= movedRight1.stack.get(height).x)) {
                System.out.println("move grid right needs checking");
            }
        }

        //testing to move the grid left: 
        for (int i = 0; i > 100; i++) {
            Grid testGrid1 = makeRandGrid();
            int height = testGrid1.stack.size() - 1;
            Grid movedLeft1 = testGrid1.onKeyEvent("left");
            if (!(testGrid1.stack.get(height).x >= movedLeft1.stack.get(height).x)) {
                System.out.println("move grid right needs checking");
            }
        }

        //testing to see if a new ingredient drops when down is pressed 
        for (int i = 0; i < 100; i++) {
            Grid testGrid = makeRandGrid();
            int stackSize = testGrid.stack.size();
            Grid addedIng = testGrid.onKeyEvent("down");
            int addedStackSize = addedIng.stack.size();
            if (!(stackSize <= addedStackSize)) {
                System.out.println("on key event down needs checking");
            }
        }

        //test to see if dropRandIngredient works 
        for (int i = 0; i < 100; i++) {
            Grid testGrid = makeRandGrid();
            int stackSize = testGrid.stack.size();
            Grid addedIng = testGrid.dropRandIngredient();
            int addedStackSize = addedIng.stack.size();
            if (!(stackSize <= addedStackSize)) {
                System.out.println("dropRandIngredient needs checking");
            }
        }
    }

}
