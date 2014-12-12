/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game2;

import static Game2.Ingredient.randColor;
import static Game2.Order.makeRandOrder;
//import Game2.Order.makeRandoOrder;
import java.util.ArrayList;
import java.util.Random;
import javalib.colors.*;
import javalib.worldimages.*;
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
    public Soul soul;

    public Grid(ArrayList<Ingredient> stack, Order o, Order c, Diner w, Soul soul) {
        this.stack = stack;
        this.ticket = o;
        this.check = c;
        this.oldWorld = w;
        this.soul = soul;
    }

    public WorldImage makeImage() {
        WorldImage back = new RectangleImage(new Posn(0, 0), 780, 860, new Black());
        WorldImage frame = new FrameImage(new Posn(0, 0), 781, 861, new Red());
        WorldImage lostSoul = new DiskImage(new Posn(740, 340), 10, new Blue());
        WorldImage backframe = new OverlayImages(back, frame);
        WorldImage backdrop = new OverlayImages(backframe, lostSoul);
        String mustStack1 = "Hello I am a lost soul tied to this world. Please free me by stacking: "
                + ticket.blue + " blue, " + ticket.green + " green, " + ticket.red
                + " red, and " + ticket.yellow + " yellow";
        WorldImage Instructions = new TextImage(new Posn(740, 285),
                "But please be careful. If you stack more than 15 blocks I won't be freed", new Red());
        WorldImage mustStackImage = new TextImage(new Posn(740, 230), mustStack1, new Red());
        WorldImage mustStack = new OverlayImages(mustStackImage, Instructions);
        WorldImage backScore = new OverlayImages(backdrop, mustStack);
        String score = "Stacked: " + check.blue + " blue " + check.green
                + " green " + check.red + " red " + check.yellow + " yellow";;
        WorldImage scoreBoard = new TextImage(new Posn(740, 430), score, new Red());
        WorldImage backScoreBoard = new OverlayImages(backScore, scoreBoard);

        for (int i = 0; i < stack.size(); i++) {
            WorldImage b = stack.get(i).drawIngredient();
            backScoreBoard = new OverlayImages(backScoreBoard, b);
        }
        return backScoreBoard;

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
        return new Grid(tempStack, this.ticket, this.check, this.oldWorld, this.soul);
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
        return new Grid(tempStack, this.ticket, this.check, this.oldWorld, this.soul);
    }

    public Grid dropRandIngredient() {
        int randoX = randNum(1, 18);
        Ingredient newIng = new Ingredient(randoX, 1, randColor(), false);
        ArrayList<Ingredient> addedStack = new ArrayList();
        for (int i = 0; i < this.stack.size(); i++) {
            addedStack.add(this.stack.get(i));
        }
        addedStack.add(newIng);
        return new Grid(addedStack, this.ticket, this.check, this.oldWorld, this.soul);
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

    public boolean looseHuh() {
        return countStacked() > 15;
    }

 
    public Grid stackIngredients() {
        ArrayList<Ingredient> tempStack = new ArrayList<>();
        for (int i = 0; i < stack.size(); i++) {
            Ingredient current = stack.get(i);
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
        return new Grid(tempStack, this.ticket, tempO, this.oldWorld, this.soul);
    }

    public World onTick() {
        //dont know how to test loosing and winning conditions of on tick 
        //      I guess maybe make a random winning grid? does that defeat the purpose?
        if (this.looseHuh()) {
            Soul newPlayer = new Soul(oldWorld.player.x - 1, oldWorld.player.y - 1);
            Diner newDiner = new Diner(newPlayer, oldWorld.souls);
            return newDiner;
        } else if (this.winHuh()) {
            ArrayList<Soul> souls = new ArrayList<>();
            for (int i = 0; i < oldWorld.souls.size(); i++) {
                Soul current = oldWorld.souls.get(i);
                if (!current.equals(soul)) {
                    souls.add(current);
                } else {
                    Soul savedSoul = new Soul(current.x, current.y, current.order, true);
                    souls.add(savedSoul);
                }
            }
            Diner newDiner = new Diner(oldWorld.player, souls);
            return newDiner;
        }
        return this.stackIngredients();
    }

    public static int randStatNum(int min, int max) {
        Random rando = new Random();
        int randomNum = rando.nextInt((max - min) + 1) + min;
        return randomNum;
    }

    public static Grid makeRandGrid() {
        ArrayList<Ingredient> arg = new ArrayList<>();
        int randX = randStatNum(1, 18);
        Ingredient bun = new Ingredient(randX, 21, "white", true);
        arg.add(bun);
        int randHeight = randStatNum(1, 10);
        int green = 0;
        int blue = 0;
        int yellow = 0;
        int red = 0;
        for (int i = 0; i < randHeight; i++) {
            String randColor = randColor();
            switch (randColor) {
                case "blue":
                    blue = blue + 1;
                case "green":
                    green = green + 1;
                case "yellow":
                    yellow = yellow + 1;
                case "red":
                    red = red + 1;
                default:
            }
            arg.add(new Ingredient(randX, 20 - i, randColor, true));
        }
        int randFall = randStatNum(1, 7);
        for (int i = 0; i < randFall; i++) {
            int randx = randStatNum(1, 18);
            int randy = randStatNum(1, 20);
            arg.add(new Ingredient(randx, randy, randColor(), false));
        }
        //idk what to give it for order, or for the old world, but for right now whatever. 
        return new Grid(arg, new Order(5, 5, 5, 5), new Order(green, blue, yellow, green), null, null);
    }

    public static void testGrid() {

        //MoveStackLeft:
        for (int i = 0; i > 100; i++) {
            Grid testGrid = makeRandGrid();
            int height = testGrid.stack.size() - 1;
            Grid movedLeft = testGrid.moveStackLeft();
            if (!(testGrid.stack.get(height).x <= movedLeft.stack.get(height).x)) {
                System.out.println("Grid: move grid right needs checking");
            }
        }

        //MoveStackRight:
        for (int i = 0; i > 100; i++) {
            Grid testGrid = makeRandGrid();
            int height = testGrid.stack.size() - 1;
            Grid movedRight = testGrid.moveStackRight();
            if (!(testGrid.stack.get(height).x <= movedRight.stack.get(height).x)) {
                System.out.println("Grid: move grid right needs checking");
            }
        }

        //DropRandIngredient:  
        for (int i = 0; i < 100; i++) {
            Grid testGrid = makeRandGrid();
            int stackSize = testGrid.stack.size();
            Grid addedIng = testGrid.dropRandIngredient();
            int addedStackSize = addedIng.stack.size();
            if (!(stackSize <= addedStackSize)) {
                System.out.println("Grid: dropRandIngredient needs checking");
            }
        }

        //OnKeyEvent:
        //move the grid right :
        for (int i = 0; i > 100; i++) {
            Grid testGrid = makeRandGrid();
            int height = testGrid.stack.size() - 1;
            Grid movedRight = testGrid.onKeyEvent("right");
            if (!(testGrid.stack.get(height).x <= movedRight.stack.get(height).x)) {
                System.out.println("Grid: move grid right needs checking");
            }
        }

        //move the grid left: 
        for (int i = 0; i > 100; i++) {
            Grid testGrid = makeRandGrid();
            int height = testGrid.stack.size() - 1;
            Grid movedLeft = testGrid.onKeyEvent("left");
            if (!(testGrid.stack.get(height).x >= movedLeft.stack.get(height).x)) {
                System.out.println("Grid: move grid right needs checking");
            }
        }

        //down arrow pressed: 
        for (int i = 0; i < 100; i++) {
            Grid testGrid = makeRandGrid();
            int stackSize = testGrid.stack.size();
            Grid addedIng = testGrid.onKeyEvent("down");
            int addedStackSize = addedIng.stack.size();
            if (!(stackSize <= addedStackSize)) {
                System.out.println("Grid: on key event down needs checking");
            }
        }

        //StackIngredients: 
        for (int i = 0; i < 100; i++) {
            Grid testGrid = makeRandGrid();
            Grid stackedGrid = testGrid.stackIngredients();
            int stackSize = testGrid.stack.size();
            for (int j = 0; j < stackSize; j++) {
                Ingredient current = testGrid.stack.get(j);
                Ingredient stackedCurrent = stackedGrid.stack.get(j);
                // the Ing was falling
                if (!current.stacked && !stackedCurrent.stacked) {
                    if (!(current.y < stackedCurrent.y)) {
                        System.out.println("Grid: stackIngredients needs checking");
                    }
                    // the Ing was not falling
                    //needs to have been stacked 
                } else if (current.stacked && !stackedCurrent.stacked) {
                    System.out.println("Grid: stackIngredients is not stacking");
                }
            }
        }

        //OnTick:
        for (int i = 0; i < 100; i++) {
            Grid testGrid = makeRandGrid();
            Grid stackedGrid = testGrid.stackIngredients();
            int stackSize = testGrid.stack.size();
            for (int j = 0; j < stackSize; j++) {
                Ingredient current = testGrid.stack.get(j);
                Ingredient stackedCurrent = stackedGrid.stack.get(j);
                // the Ing was falling
                if (!current.stacked && !stackedCurrent.stacked) {
                    if (!(current.y < stackedCurrent.y)) {
                        System.out.println("Grid: stackIngredients needs checking");
                    }
                    // the Ing was not falling
                    //needs to have been stacked 
                } else if (current.stacked && !stackedCurrent.stacked) {
                    System.out.println("Grid: stackIngredients is not stacking");
                }
            }
        }
        
        //CountGreen:
        for (int i = 0; i < 100; i++) {
            ArrayList<Ingredient> array = new ArrayList<>();
            int colorNum = randStatNum(1,10);
            int stackX = randStatNum(1,18);
            for (int j = 0; j < colorNum; j++){
                Ingredient color = new Ingredient(stackX, 20 - j, "green", true);
                array.add(color);
            }
            Grid colorGrid = new Grid(array, new Order(colorNum + 1,1,1,1), new Order(colorNum,0,0,0), 
                    new Diner(new Soul (0,0), new ArrayList<Soul>()), new Soul(0,0));
            if (colorGrid.countGreen() != colorNum) {
                System.out.println("Grid: countGreen not working");
            }
        }
        
        //CountBlue:
        for (int i = 0; i < 100; i++) {
            ArrayList<Ingredient> array = new ArrayList<>();
            int colorNum = randStatNum(1,10);
            int stackX = randStatNum(1,18);
            for (int j = 0; j < colorNum; j++){
                Ingredient color = new Ingredient(stackX, 20 - j, "blue", true);
                array.add(color);
            }
            Grid colorGrid = new Grid(array, new Order(1,1 + colorNum,1,1), new Order(0,colorNum,0,0), 
                    new Diner(new Soul (0,0), new ArrayList<Soul>()), new Soul(0,0));
            if (colorGrid.countBlue() != colorNum) {
                System.out.println("Grid: countBlue not working");
            }
        }
        
        //CountYellow:
        for (int i = 0; i < 100; i++) {
            ArrayList<Ingredient> array = new ArrayList<>();
            int colorNum = randStatNum(1,10);
            int stackX = randStatNum(1,18);
            for (int j = 0; j < colorNum; j++){
                Ingredient color = new Ingredient(stackX, 20 - j, "yellow", true);
                array.add(color);
            }
            Grid colorGrid = new Grid(array, new Order(1,1,1 + colorNum,1), new Order(0,0,colorNum,0), 
                    new Diner(new Soul (0,0), new ArrayList<Soul>()), new Soul(0,0));
            if (colorGrid.countYellow() != colorNum) {
                System.out.println("Grid: countYellow not working");
            }
        }
        
        //CountRed:
        for (int i = 0; i < 100; i++) {
            ArrayList<Ingredient> array = new ArrayList<>();
            int colorNum = randStatNum(1,10);
            int stackX = randStatNum(1,18);
            for (int j = 0; j < colorNum; j++){
                Ingredient color = new Ingredient(stackX, 20 - j, "red", true);
                array.add(color);
            }
            Grid colorGrid = new Grid(array, new Order(1,1,1,1 + colorNum), new Order(0,0,0,colorNum), 
                    new Diner(new Soul (0,0), new ArrayList<Soul>()), new Soul(0,0));
            if (colorGrid.countRed() != colorNum) {
                System.out.println("Grid: countRed not working");
            }
        }
        
        //CountStacked:
        for (int i = 0; i < 100; i++) {
            ArrayList<Ingredient> array = new ArrayList<>();
            int stackNum = randStatNum(1,10);
            int stackX = randStatNum(1,18);
            for (int j = 0; j < stackNum; j++){
                Ingredient Ing = new Ingredient(stackX, 20 - j, randColor(), true);
                array.add(Ing);
            }
            Grid colorGrid = new Grid(array, new Order(0,0,0,1 + stackNum), new Order(0,0,0,stackNum), 
                    new Diner(new Soul (0,0), new ArrayList<Soul>()), new Soul(0,0));
            if (colorGrid.countStacked() != stackNum) {
                System.out.println("Grid: countStacked not working");
            }
        }
    }

}
