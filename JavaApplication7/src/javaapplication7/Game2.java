/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javaapplication7;

import java.util.ArrayList;
/**
 *
 * @author Isabella
 */
public class Game2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArrayList<Ingredient> arr = new ArrayList();
        Ingredient bun = new Ingredient(9,21,"white",true);
        arr.add(bun);
        Grid game = new Grid(arr, new Order(0,0,0,0));
        game.bigBang(230, 345, .1);
    }
    
}
