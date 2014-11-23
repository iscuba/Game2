/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javaapplication7;
import java.util.ArrayList;
import java.util.Random;
/**
 *
 * @author Isabella
 */
public class Grid {
    
    final Order check;
    
    protected int MAX = 18;
    protected int MIN = 1;
    public int height;
    public Order ticket;
    
    //public int green, blue, yellow, purple; 
     
    public ArrayList<Ingredient> stack;
    //public int stackX;
    
    public Grid(ArrayList<Ingredient> burger, Order o){
        this.stack = burger;
        this.ticket = o;
        this.check = Order.makeRandOrder();
        
        //this.stackX = burger.get(0).x;
    }
    
     public int randNum(int max, int min){
        Random rando = new Random();
        int randomNum = rando.nextInt((max - min) + 1) + min;
        return randomNum;
    }
    
    // my on tick uses this: 
    public Grid dropIngredients(){
        ArrayList<Ingredient> tempo = new ArrayList<>();
        for(int i=0; i<stack.size(); i++){
            Ingredient current = stack.get(i);
            if(current.stacked){
                tempo.add(current);
            } else {
                tempo.add(new Ingredient(current.x, current.y + 1, current.color, current.stacked));
            }
        }
        return new Grid(tempo, this.ticket);
    }
    
    // my on key uses this to move the stack left or right
    public Grid moveStackLeft(){
        ArrayList<Ingredient> tempStack = new ArrayList<>();
        for( int i=0; i<stack.size();i++){
            Ingredient current = stack.get(i);
            if (current.stacked){
                tempStack.add(current);
            } else if (current.x <= MIN) {
                tempStack.add(current);
            } else {
                tempStack.add(new Ingredient(current.x - 1, current.y, current.color, current.stacked));
            }
        }
        return new Grid(tempStack, this.ticket);
    }
    
    public Grid moveStackRight(){
        ArrayList<Ingredient> tempStack = new ArrayList<>();
        for( int i=0; i<this.stack.size();i++){
            Ingredient current = stack.get(i);
            if (current.stacked){
                tempStack.add(current);
            } else if (current.x >= MAX) {
                tempStack.add(current);
            } else {
                tempStack.add(new Ingredient(current.x + 1, current.y, current.color, current.stacked));
            }
        }
        return new Grid(tempStack, this.ticket);
    }
    
    public Grid onKeyEvent(String ke) {
        switch (ke) {
            case "left":
                return this.moveStackLeft();
           case "right":
                return this.moveStackRight();
            default:
                return this;
        }
    }
    
    //changes the stack to include Ingredients which have been successfully caught by the user
    public Grid stackIngredients(){
        ArrayList<Ingredient> tempStack = new ArrayList<>();
        for (int i=0; i<stack.size(); i++){
            Ingredient current = stack.get(i);
            // the falling ingredient is caught by the bun
            if (!current.stacked && stack.get(0).x == current.x && MAX - height == current.y){
                tempStack.add(current.stackIt());
                height++;
            } else {
                tempStack.add(current);
            }
        }
        Order tempO = new Order(countGreen(), countBlue(), countYellow(), countRed());
        return new Grid(tempStack, tempO);
    }
    
    public int countGreen(){
        int count = 0;
        for (int i=0; i<stack.size(); i++){
            Ingredient current = stack.get(i);
            if (current.stacked && current.color.equals("green")){
                count++;
            }
        }
        return count;
    }
    
    public int countBlue(){
        int count = 0;
        for (int i=0; i<stack.size(); i++){
            Ingredient current = stack.get(i);
            if (current.stacked && current.color.equals("blue")){
                count++;
            }
        }
        return count;
    }
    
    public int countYellow(){
        int count = 0;
        for (int i=0; i<stack.size(); i++){
            Ingredient current = stack.get(i);
            if (current.stacked && current.color.equals("yellow")){
                count++;
            }
        }
        return count;
    }
    public int countRed(){
        int count = 0;
        for (int i=0; i<stack.size(); i++){
            Ingredient current = stack.get(i);
            if (current.stacked && current.color.equals("red")){
                count++;
            }
        }
        return count;
    }
    
    public boolean winHuh(){
        return check.filled(ticket);
    }
    
    //if they stacked more than 22 things without completeing the order 
    public boolean looseHuh(){
        return height >= 22;
    }
    
    //random color function which returns a string with a color name 
    public String randColor() {
        int rando = randNum(1,4);
        switch (rando){
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
    
    public Grid dropRandIngredient(){
        int randoX = randNum(1, 18);
        Ingredient newIng = new Ingredient(randoX, 1, randColor(), false);
        ArrayList<Ingredient> addedStack = new ArrayList();
        for (int i=0;i<this.stack.size();i++){
            addedStack.add(this.stack.get(i));
        }
        addedStack.add(newIng);
        return new Grid(addedStack, this.ticket);
    }
    
    
    public Grid onTick(){ 
        // if the random number is 3 then call make new Ingredient 
        int rando = Order.randNum();
        if (rando == 3){
            //is this prone to time traveling?
            this.dropRandIngredient();
            //makes a new Ingrdient with the stacked set to false (a new dropping Ing is added to stack)
      //      return new Grid (this.stack.dropRandIngredent(), this.ticket) ;
        }
        if (looseHuh()) {
            return this;
        } else if (winHuh()) {
            return this;

        } else {
            return this.stackIngredients();
        }
    }

    
}
