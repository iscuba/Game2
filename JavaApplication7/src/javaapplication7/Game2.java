/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javaapplication7;

import java.util.ArrayList;
import static javaapplication7.Grid.testGrid;
/**
 *
 * @author Isabella
 */
public class Game2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        testGrid();
        Diner game = new Diner(new Soul(0,15));
        game.bigBang(1080, 500, .1);
//        ArrayList<Ingredient> arr = new ArrayList();
//        Ingredient bun = new Ingredient(9,21,"white",true);
//        arr.add(bun);
//        Grid game = new Grid(arr, new Order(4,2,3,0));
//        game.bigBang(1080, 420, .1);
    }
    
}
