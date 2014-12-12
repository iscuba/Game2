/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game2;

import java.util.Random;

/**
 *
 * @author Isabella
 */
public class Order {

    public int green, blue, yellow, red;

    public Order(int g, int b, int y, int p) {
        this.green = g;
        this.blue = b;
        this.yellow = y;
        this.red = p;
    }

    public boolean filled(Order ticket) {
        return this.green >= ticket.green
                && this.blue >= ticket.blue
                && this.yellow >= ticket.yellow
                && this.red >= ticket.red;
    }

    //returns random number from 1 - 5 inc
    public static int randNum() {
        int max = 5;
        int min = 1;
        Random rando = new Random();
        int randomNum = rando.nextInt((max - min) + 1) + min;
        return randomNum;
    }

    public static Order makeRandOrder() {
        return new Order(randNum(), randNum(), randNum(), randNum());
    }
    
    public static void testOrder(){
        
        //Filled
        for (int i = 0; i<100; i++){
            Order testOrder = makeRandOrder();
            if (!testOrder.filled(testOrder)){
                System.out.println("need to check Order: filled");
            }
        }
    }

}
